package org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;


public class Vehiculos implements IVehiculos {

    private static final String COLECCION = "vehiculos";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";
    private static Vehiculos instancia;
    private static MongoCollection<Document> coleccionVehiculos;
    private String matricula;
    private String marca;
    private String modelo;


    static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    private Bson getCriterioBusqueda(Vehiculo vehiculo) {
        return eq(MATRICULA, vehiculo.matricula());
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    @Override
    public void comenzar() {
        coleccionVehiculos = MongoDB.getBD().getCollection(COLECCION);

    }

    Vehiculo getVehiculo(Document documento) {
        Vehiculo vehiculo = null;
        if (documento != null) {
            vehiculo = new Vehiculo(documento.getString(MARCA), documento.getString(MODELO), documento.getString(MATRICULA));
        }
        return vehiculo;
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();

    }

    @Override
    public List<Vehiculo> get() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Document documento : coleccionVehiculos.find()) {
            vehiculos.add(getVehiculo(documento));
        }
        return vehiculos;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

        Objects.requireNonNull(vehiculo, "No se puede insertar un vehiculo nulo.");
        FindOneAndReplaceOptions opciones = new FindOneAndReplaceOptions().upsert(true);
        Document resultado = coleccionVehiculos.findOneAndReplace(getCriterioBusqueda(vehiculo), getDocumento(vehiculo), opciones);
        if (resultado != null) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehiculo nulo.");
        return getVehiculo(coleccionVehiculos.find(getCriterioBusqueda(vehiculo)).first());
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

    }

    Document getDocumento(Vehiculo vehiculo) throws OperationNotSupportedException {
        Document documento = null;
        if (vehiculo != null) {
            String matricula = vehiculo.matricula();
            String marca = vehiculo.marca();
            String modelo = vehiculo.modelo();
            documento = new Document().append(MATRICULA, matricula).append(MARCA, marca).append(MODELO, modelo);
        }
        return documento;
    }

}