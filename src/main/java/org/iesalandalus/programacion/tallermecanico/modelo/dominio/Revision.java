package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);

    }

    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        fechaFin = revision.fechaFin;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
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

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        } else if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial = this.precioMaterial + precioMaterial;
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
        float precioTotal = 0;
        if (estaCerrada()) {
            float precioTotalHoras = getHoras() * PRECIO_HORA;
            float precioTotalDias = getDias() * PRECIO_DIA;
            float precioTotalMaterial = getPrecioMaterial() * PRECIO_MATERIAL;
            precioTotal = precioTotalHoras + precioTotalDias + precioTotalMaterial;
        }
        return precioTotal;
    }

    private float getDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
    @Override
    public String toString() {
        String x;
        String revision = String.format("%s - %s: (%s", this.cliente, this.vehiculo, this.fechaInicio.format(FORMATO_FECHA));

        if (estaCerrada()) {
            x = String.format("%s - %s), %s horas, %4.2f € en material, %4.2f € total",
                    revision, this.fechaFin.format(FORMATO_FECHA), this.horas,
                    this.precioMaterial, getPrecio());
        } else {
            x = String.format("%s - ), %s horas, %4.2f € en material",
                    revision, this.horas, this.precioMaterial);
        }
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revision revision)) return false;
        if (((Revision) o).cliente.equals(cliente) || ((Revision) o).vehiculo.equals(vehiculo) || ((Revision) o).fechaInicio.equals(fechaInicio))
            return true;
        return getHoras() == revision.getHoras() && Float.compare(getPrecioMaterial(), revision.getPrecioMaterial()) == 0 && Objects.equals(getFechaInicio(), revision.getFechaInicio()) && Objects.equals(getFechaFin(), revision.getFechaFin()) && Objects.equals(getCliente(), revision.getCliente()) && Objects.equals(getVehiculo(), revision.getVehiculo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFechaInicio(), getCliente(), getVehiculo());
    }
}
