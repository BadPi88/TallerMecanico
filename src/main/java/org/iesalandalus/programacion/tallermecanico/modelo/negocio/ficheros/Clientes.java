package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private List<Cliente> coleccionClientes;
    private static final String FICHEROS_CLIENTES = String.format("%s%s%s", "resources", File.separator, "clientes.xml");
    private static final String RAIZ = "clientes";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";
    private static Clientes instacia;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }


    // patron singlenton para que solo haya una instacia a la vez y es global
    static Clientes getInstancia() {
        if (instacia == null) {
            instacia = new Clientes();
        }
        return instacia;
    }


    @Override
    public List<Cliente> get() {

        return new ArrayList<>(coleccionClientes);
    }
    @Override
    public void comenzar() {
        Document documentoXML = UtilidadesXml.leerDocumentoXml(FICHEROS_CLIENTES);
        if (documentoXML != null) {
            System.out.println("Fichero %s leido correctamente." + FICHEROS_CLIENTES);
        }
    }

    @Override
    public void terminar() {
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml,FICHEROS_CLIENTES);

    }

    private void procesarDocumentoXml(Document docmuentoXml) {
        NodeList clientes = docmuentoXml.getElementsByTagName(CLIENTE);
        for (int i = 0; i < clientes.getLength(); i++) {
            Node cliente = clientes.item(i);
            try {
                if (cliente.getNodeType() == Node.ELEMENT_NODE) {
                    insertar(getCliente((Element) cliente));
                }

            } catch (OperationNotSupportedException e) {
                System.out.printf("Error al leer el cliente.",e.getMessage());
            }

        }
    }

    private Cliente getCliente(Element elemento) {
        String nombre = elemento.getAttribute(NOMBRE);
        String dni = elemento.getAttribute(DNI);
        String telefono = elemento.getAttribute(TELEFONO);
        return new Cliente(nombre, dni, telefono);

    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Cliente cliente : coleccionClientes){
                Element elemento = getElemento(documentoXml,cliente);
                if (elemento.getNodeType() == Node.ELEMENT_NODE){
                    documentoXml.getDocumentElement().appendChild(elemento);
                }
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml,Cliente cliente){
        Element elemento = documentoXml.createElement(CLIENTE);
        elemento.setAttribute(NOMBRE,cliente.getNombre());
        elemento.setAttribute(DNI,cliente.getDni());
        elemento.setAttribute(TELEFONO,cliente.getTelefono());
        return elemento;

    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");

        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (indice == -1) ? null : coleccionClientes.get(indice);

    }

    @Override
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


    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (buscar(cliente) == null) {

            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }

        coleccionClientes.remove(cliente);
    }
}