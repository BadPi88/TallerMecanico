package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Mecanico extends Trabajo{
    private float FACTOR_HORA = 30F;
    private float FACTOR_PRECIO_MATERIAL = 1.5F;
    protected float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaIncio) {
        super(cliente,vehiculo,fechaIncio);
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaIncio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        } else if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial = this.precioMaterial + precioMaterial;
    }
    public float getPrecioEspecifico(){
        return (FACTOR_HORA * getHoras())+ (precioMaterial * FACTOR_PRECIO_MATERIAL);
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "FACTOR_HORA=" +
                ", precioMaterial=" + precioMaterial +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horas=" + horas +
                ", cliente=" + cliente +
                ", vehiculo=" + vehiculo +
                '}';
    }
}
