package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {

    private static String FICHERO_TRABAJOS;
    private static DateTimeFormatter FORMATO_FECHA;
    private static String RAIZ;
    private static String TRABAJO;
    private static String CLIENTE;
    private static String VEHICULO;
    private static String FECHA_INICIO;
    private static String FECHA_FIN;
    private static String HORAS;
    private static String PRECIO_MATERIAL;
    private static String TIPO;
    private static String REVISION;
    private static String MECANICO;


    private final List<Trabajo> coleccionTrabajos;
    private Vehiculo vehiculo;

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

        Trabajo trabajoEncontrado = trabajo;
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

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No puedo añadir precio del material a un trabajo nulo.");
        Trabajo trabajoEncontrado = Trabajo.get(vehiculo);
        if (trabajoEncontrado.estaCerrada())
            throw new OperationNotSupportedException("No existe ningún trabajo abierto para dicho vehículo.");
        return trabajoEncontrado;
    }

    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) {
        Map<TipoTrabajo, Integer> estadisticas = inicializarEstadisticas();
        int i = 0;
        int x = 0;
        for (Trabajo trabajo : coleccionTrabajos ){
            if (trabajo instanceof Revision){

                estadisticas.put(TipoTrabajo.REVISION,i);
            }
            if (trabajo instanceof Mecanico){
                estadisticas.put(TipoTrabajo.MECANICO,x);
            }
        }
        return new EnumMap<>(TipoTrabajo.class);

    }

    private Map<TipoTrabajo, Integer> inicializarEstadisticas() {
        Map<TipoTrabajo, Integer> estadisticas = new HashMap<>();

            estadisticas.put(TipoTrabajo.REVISION, 0);
            estadisticas.put(TipoTrabajo.MECANICO, 0);

        return estadisticas;
    }
}

