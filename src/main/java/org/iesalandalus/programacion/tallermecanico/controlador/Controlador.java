package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {
    private final ModeloCascada modelo;
    private final Vista vista;

    public Controlador(ModeloCascada modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = new ModeloCascada();
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        modelo.insertar(vehiculo);
    }

    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        modelo.insertar(trabajo);
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo trabajo) {
        return modelo.buscar(trabajo);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return modelo.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        modelo.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        modelo.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        modelo.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        modelo.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        modelo.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        modelo.borrar(revision);
    }

    public List<Cliente> getClientes() {
        return modelo.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modelo.getVehiculos();
    }

    public List<Trabajo> getTrabajos() {
        return modelo.getTrabajos();
    }

    public List<Trabajo> getTrabajos(Cliente cliente) {
        return modelo.getTrabajos();
    }

    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return modelo.getTrabajos();
    }

}