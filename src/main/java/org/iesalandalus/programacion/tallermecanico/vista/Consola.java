package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {
    }

    public static void mostrarCabecera(String mensaje) {

        System.out.println(mensaje);

    }

    public static void mostrarMenu() {
        mostrarCabecera("¡HOLA! Bienvenido al sistema de gestión de revisiones del taller mecánico.");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        Opcion numeroOpcion = null;
        do {
            numeroOpcion = Opcion.get(leerEntero("Seleccione la opción:"));

        } while (numeroOpcion == null);
        return numeroOpcion;
    }

    private static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }


    public static Cliente leerCliente() {
        String dni = leerCadena("Introduzca el DNI del cliente:");
        String nombre = leerNuevoNombre();
        String telefono = leerNuevoTelefono();
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduzca el DNI del cliente:");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduzca el nombre del cliente:");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduzca el número de teléfono del cliente:");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduzca la marca del vehículo:");
        String modelo = leerCadena("Introduzca el modelo del vehículo:");
        String matricula = leerCadena("Introduzca la matrícula del vehículo:");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduzca la matrícula del vehículo:");
        return Vehiculo.get(matricula);
    }

    public static Trabajo leerTrabajo() {
        return new Trabajo(leerClienteDni(), leerVehiculoMatricula(), leerFecha("Introduzca la fecha de inicio de la revisión:")) {
            @Override
            public float getPrecioEspecifico() {
                return 0;
            }
        };
    }

    public static int leerHoras() {
        return leerEntero("Introduzca el número de horas trabajadas:");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduzca el precio del material gastado:");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduzca la fecha de cierre de la revisión:");
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha;
        formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        try {
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);

        } catch (DateTimeParseException e) {

        }
        return fecha;
    }
}
