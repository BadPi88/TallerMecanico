package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private List<Cliente> coleccionClientes;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }


    public List<Cliente> get() {

        return new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");

        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (indice == -1) ? null : coleccionClientes.get(indice);

    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteBuscado = buscar(cliente);
        if (clienteBuscado == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        boolean modificado = false;
        if (nombre != null && !nombre.isBlank()) {
            clienteBuscado.setNombre(nombre);
            modificado = true;
        }
        if (telefono != null && !telefono.isBlank()) {
            clienteBuscado.setTelefono(telefono);
            modificado = true;
        }

        return modificado;
    }


    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (buscar(cliente) == null) {

            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }

        coleccionClientes.remove(cliente);
    }
}