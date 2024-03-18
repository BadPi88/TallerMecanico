package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos {

    private final List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionClientes = new ArrayList<>();

        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionClientes.add(trabajo);
            }
        }
        return revisionClientes;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> listaTemporal = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                listaTemporal.add(trabajo);
            }
        }
        return listaTemporal;
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente) && !trabajo.estaCerrada()) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
            }
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrada()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en el taller.");
            }
            if (trabajo.estaCerrada() && trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
            }
            if (trabajo.estaCerrada() && trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene otro trabajo posterior.");
            }
        }
    }


    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");

        Cliente cliente = trabajo.getCliente();
        Vehiculo vehiculo = trabajo.getVehiculo();
        LocalDate fechaRevision = trabajo.getFechaInicio();
        comprobarRevision(cliente, vehiculo, fechaRevision);

        coleccionTrabajos.add(trabajo);
    }


    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        Trabajo revisionEncontrada = buscar(trabajo);
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        revisionEncontrada.anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {

        Trabajo trabajoEncontrado = getTrabajoAbierto(trabajo);
        trabajoEncontrado.getPrecioEspecifico();
        if (trabajoEncontrado instanceof Mecanico) {
            ((Mecanico) trabajoEncontrado).anadirPrecioMaterial(precioMaterial);

        } else {
            throw new OperationNotSupportedException("No se puede añadir precio al material para este tipo de trabajos.");
        }
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");

        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        trabajo.cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");

        int indice = coleccionTrabajos.indexOf(trabajo);

        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ningún trabajo igual.");
        }
        coleccionTrabajos.remove(indice);
    }

    private Trabajo getTrabajoAbierto(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        Trabajo trabajoEncontrado = buscar(trabajo);
        if (trabajoEncontrado == null) {

            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoEncontrado;

    }
}

