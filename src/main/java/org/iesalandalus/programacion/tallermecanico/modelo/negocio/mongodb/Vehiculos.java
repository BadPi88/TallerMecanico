package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb;

import org.bson.Document;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class Vehiculos implements IVehiculos {

    private static Vehiculos instancia;
    static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }
    @Override
    public void comenzar() {

    }

    @Override
    public void terminar() {

    }

    @Override
    public List<Vehiculo> get() {
        return null;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return null;
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }

    public Vehiculo getVehiculo(Document document) {
        return null;
    }

    public Document getDocumento(Vehiculo vehiculo) {
        return null;
    }
}
