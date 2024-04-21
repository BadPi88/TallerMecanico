package org.iesalandalus.programacion.tallermecanico.vista;


import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface Vista {

    LocalDate leerFechaCierre();

    GestorEventos getGestorEventos();

    void comenzar();

    void terminar();

    Cliente leerCliente();

    Cliente leerClienteDni();

    String leerNuevoNombre();

    String leerNuevoTelefono();

    Vehiculo leerVehiculo();

    Vehiculo leerMatriculaVehiculo();

    Trabajo leerRevision();

    Trabajo leerMecanico();

    Trabajo leerTrabajoVehiculo();

    int leerHoras();

    float leerPrecioMaterial();

    void notificarEvento(Evento evento, String texto, boolean existe);

    void mostrarCliente(Cliente cliente);

    void mostrarVehiculos(Vehiculo vehiculo);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarClientes(List<Cliente> clientes);

    void mostrarVehiculos(List<Vehiculo> vehiculos);

    void mostrarTrabajos(List<Trabajo> trabajos);

    void mostrarTrabajosCliente(List<Trabajo> trabajosCliente);

    void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo);

    void mostrarEstadisticasMensuales(Map<TipoTrabajo,Integer> estadisticas);

    LocalDate leerMes();

}