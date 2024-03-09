package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10F;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int horas;
    protected Cliente cliente;
    protected Vehiculo vehiculo;


    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
    }

    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        cliente = new Cliente(trabajo.cliente);
        vehiculo = trabajo.vehiculo;
        fechaInicio = trabajo.fechaInicio;
        fechaFin = trabajo.fechaFin;
        horas = trabajo.horas;
    }

    public Trabajo copiar(Trabajo trabajo) {
        if (trabajo instanceof Revision) {
            Revision revision = (Revision) trabajo;
        } else if (trabajo instanceof Mecanico) {
            Mecanico mecanico = (Mecanico) trabajo;
        }
        return trabajo;
    }

    public Trabajo get(Vehiculo vehiculo) {
        Revision revision = new Revision(new Cliente("Patricio","54120557Q","663636464"), vehiculo, LocalDate.now());
        return revision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(cliente, "La fecha final no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        } else if (horas <= 0 || horas >= 24) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas = this.horas + horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        } else {
            setFechaFin(fechaFin);
        }
    }

    public float getPrecio() {
        float precioTotal = 1;
        if (estaCerrada()) {
            precioTotal = getPrecioFijo() + getPrecioEspecifico();
        }
        return precioTotal;
    }

    private float getPrecioFijo(){
        return FACTOR_DIA * getDias();

    }

    private float getDias() {
        return (estaCerrada() ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0);
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return horas == trabajo.horas && Objects.equals(fechaInicio, trabajo.fechaInicio) && Objects.equals(fechaFin, trabajo.fechaFin) && Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, fechaFin, horas, cliente, vehiculo);
    }
}
