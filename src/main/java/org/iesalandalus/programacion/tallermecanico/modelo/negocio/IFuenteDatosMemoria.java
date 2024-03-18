package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

public interface IFuenteDatosMemoria {
    IClientes crearClientes();

    IVehiculos crearVehiculos();

    ITrabajos crearAlquileres();
}
