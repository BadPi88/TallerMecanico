package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {
    private final float FACTOR_HORA = 30F;
    private final float FACTOR_PRECIO_MATERIAL = 1.5F;
    protected float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaIncio) {
        super(cliente, vehiculo, fechaIncio);
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaIncio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        cliente = new Cliente(mecanico.cliente);
        vehiculo = mecanico.vehiculo;
        fechaInicio = mecanico.fechaInicio;
        precioMaterial = mecanico.precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        } else if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial = this.precioMaterial + precioMaterial;
    }

    public float getPrecioEspecifico() {
        return (FACTOR_HORA * getHoras()) + (precioMaterial * FACTOR_PRECIO_MATERIAL);
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mecanico mecanico = (Mecanico) o;
        return Float.compare(precioMaterial, mecanico.precioMaterial) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        String x;
        String revision = String.format("Mecánico -> %s - %s (%s", this.cliente, this.vehiculo, this.fechaInicio.format(FORMATO_FECHA));

        if (estaCerrada()) {
            x = String.format("%s - %s): %s horas, %4.2f € en material, %4.2f € total",
                    revision, this.fechaFin.format(FORMATO_FECHA), this.horas, getPrecioMaterial(),getPrecio());
        } else {
            x = String.format("%s - ): %s horas, %4.2f € en material",
                    revision, this.horas,getPrecioMaterial());
        }
        return x;
    }
}