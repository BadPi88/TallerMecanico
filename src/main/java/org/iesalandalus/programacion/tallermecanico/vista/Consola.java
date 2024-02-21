package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){

    }
    public static void mostrarCabecera (String mensaje) {
        System.out.print(mensaje);
        for(int i = 0; i < mensaje.length(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void mostrarMenu(){
        mostrarCabecera("¡Bienvenido al sistema de gestión del taller mecánico!");
        for (Opcion opcion : Opcion.values()){
            System.out.println(opcion);
        }
    }
    public static Opcion elegirOpcion() {
        int numeroOpcion;
        do {
            numeroOpcion = leerEntero("Por favor, seleccione el número correspondiente a la opción que desee:");
        } while (Opcion.esValida(numeroOpcion));
        return Opcion.get(numeroOpcion);
    }
    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Entrada.entero();
    }
    private static float leerReal(String mensaje){
        System.out.println(mensaje);
        return Entrada.real();
    }
    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }
    private static LocalDate leerFecha(String mensaje){
        System.out.println(mensaje);
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        String fechaString = Entrada.cadena();
        fecha = LocalDate.parse(fechaString, formatoFecha);
        return fecha;
    }
    public static Cliente leerCliente(){
        String dni = leerCadena("Por favor, introduzca el DNI del cliente:");
        String nombre = leerNuevoNombre();
        String telefono = leerNuevoTelefono();
        return new Cliente(nombre, dni, telefono);
    }
    public static Cliente leerClienteDni(){
        String dni = leerCadena("Por favor, introduzca el DNI del cliente:");
        return Cliente.get(dni);
    }
    public static String leerNuevoNombre(){
        return leerCadena("Por favor, introduzca el nombre del cliente:");
    }
    public static String leerNuevoTelefono(){
        return leerCadena("Por favor, introduzca el número de teléfono del cliente:");
    }
    public static Vehiculo leerVehiculo(){
        String marca = leerCadena("Por favor, introduzca la marca del vehículo:");
        String modelo = leerCadena("Por favor, introduzca el modelo del vehículo:");
        String matricula = leerCadena("Por favor, introduzca la matrícula del vehículo:");
        return new Vehiculo(marca, modelo, matricula);
    }
    public static Vehiculo leerVehiculoMatricula(){
        String matricula = leerCadena("Por favor, introduzca la matrícula del vehículo:");
        return Vehiculo.get(matricula);
    }
    public static Revision leerRevision(){
        return new Revision(leerCliente(), leerVehiculo(), leerFecha("Por favor, introduzca la fecha de inicio de la revisión:"));
    }
    public static int leerHoras(){
        return leerEntero("Por favor, introduzca el número de horas trabajadas:");
    }
    public static float leerPrecioMaterial(){
        return leerReal("Por favor, introduzca el precio del material usado:");
    }
    public static LocalDate leerFechaCierre(){
        return leerFecha("Por favor, introduzca la fecha de cierre de la revisión:");
    }
}
