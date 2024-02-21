package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Vista {

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador,"EL controlador no puede ser nulo.");
    }
    public  void comenzar() throws OperationNotSupportedException {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();

        }while (opcion != Opcion.SALIR);

    }
    public  void terminar(){
        System.out.println("Hasta luego Lucas");
    }


}