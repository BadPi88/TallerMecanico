package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo {
    private float FACTOR_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo,fechaInicio);
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;

    }

    public Revision(Revision revision) {
        super(revision);
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        fechaFin = revision.fechaFin;
        horas = revision.horas;
    }

    
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA;
    }


    @Override
    public String toString() {
        String x;
        String revision = String.format("Revisión -> %s - %s (%s",this.cliente, this.vehiculo, this.fechaInicio.format(FORMATO_FECHA));

        if (estaCerrada()) {
            x = String.format("%s - %s), %s horas, %4.2f € total",
                    revision, this.fechaFin.format(FORMATO_FECHA), this.horas, getPrecio());
        } else {
            x = String.format("%s - ): %s horas",
                    revision, this.horas);
        }
        return x;
    }

}
