package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements org.iesalandalus.programacion.tallermecanico.modelo.Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Trabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fuenteDatos) {
    }

    @Override
    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        trabajos = new Trabajos();
    }

    @Override
    public void terminar() {
        System.out.print("El modelo ha terminado.");
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "Alquiler NuloError al Insertar");
        Cliente cliente = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(trabajo.getVehiculo());
        trabajos.insertar(new Trabajo(cliente, vehiculo, trabajo.getFechaInicio()) {
            @Override
            public float getPrecioEspecifico() {
                return 0;
            }
        });
    }

    //Cliente buscado comprobamos si no es nulo, es decir que existe.
    @Override
    public Cliente buscar(Cliente cliente) {
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual");
        return new Cliente(cliente);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        vehiculo = Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehiculo igual");
        return vehiculo;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        trabajo = Objects.requireNonNull(trabajos.buscar(trabajo), "No existe una trabajo igual");
        return new Trabajo(trabajo) {
            @Override
            public float getPrecioEspecifico() {
                return 0;
            }
        };
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(cliente)) {
            borrar(trabajo.getCliente());

        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos() {

        List<Trabajo> listaRevisiones = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            listaRevisiones.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }
        return listaRevisiones;
    }

    @Override
    public List<Trabajo> getRevisiones(Cliente cliente) {
        List<Trabajo> listaRevisionesConCliente = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            listaRevisionesConCliente.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }
        return listaRevisionesConCliente;
    }

    @Override
    public List<Trabajo> getRevisiones(Vehiculo vehiculo) {
        List<Trabajo> listaRevisionesConVehiculo = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            listaRevisionesConVehiculo.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }

        return listaRevisionesConVehiculo;
    }


}