package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionClientes = new ArrayList<>();

        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionClientes.add(revision);
            }
        }
        return revisionClientes;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> listaTemporal = new ArrayList<>();
        for (Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                listaTemporal.add(revision);
            }
        }
        return listaTemporal;
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
            }
            if (revision.getVehiculo().equals(vehiculo) && !revision.estaCerrada()) {
                throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
            }
            if (revision.estaCerrada() && revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
            }
            if (revision.estaCerrada() && revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
            }
        }
    }


    public void insertar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");

        Cliente cliente = revision.getCliente();
        Vehiculo vehiculo = revision.getVehiculo();
        LocalDate fechaRevision = revision.getFechaInicio();

        comprobarRevision(cliente, vehiculo, fechaRevision);

        coleccionRevisiones.add(revision);
    }


    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Revision revisionEncontrada = buscar(revision);
        if (!coleccionRevisiones.contains(revision)) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        revisionEncontrada.anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {

        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");

        if (!coleccionRevisiones.contains(revision)) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        revision.cerrar(fechaFin);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int indice = coleccionRevisiones.indexOf(revision);

        return (indice == -1)? null : coleccionRevisiones.get(indice);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");

        int indice = coleccionRevisiones.indexOf(revision);

        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(indice);
    }

    private Revision getRevision(Revision revision) throws OperationNotSupportedException {
        Objects.requireNonNull(revision, "la revision no puede ser nula");
        Revision revisionEncontrada = buscar(revision);
        if (revisionEncontrada == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        return revisionEncontrada;

    }
}