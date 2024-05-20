package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

import java.io.IOException;

public class BorrarCliente extends Controlador {

    @FXML
    private TextField tfDni;

    @FXML
    void ratonCancelar(MouseEvent event) {
        tfDni.clear();
        getEscenario().close();

    }

    @FXML
    void ratonConfirmar(MouseEvent event) {
        try{
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_CLIENTE);
            Dialogos.mostrarDialogoConfirmacion("Estas seguro de que quieres borrarlo?","El cliente se ha borrado correctamente",getEscenario());

        }catch (Exception e){
            Dialogos.mostrarDialogoError("ERROR AL BORRAR EL CLIENTE",e.getMessage(),getEscenario());

        }

    }

}
