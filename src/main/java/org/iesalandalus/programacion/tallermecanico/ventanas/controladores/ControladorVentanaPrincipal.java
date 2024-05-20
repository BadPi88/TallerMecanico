package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

public class ControladorVentanaPrincipal extends Controlador {

    @FXML
    private Button btCliente;

    @FXML
    private Button btTrabajos;

    @FXML
    private Button btVehiculo;

    private static Scene escenaPrincipal;

    public static void setEscenaPrincipal(Scene escena){
        if (escenaPrincipal == null){
            escenaPrincipal = escena;
        }
    }
    public Scene getEscenaPrincipal(){
        return escenaPrincipal;
    }
    @FXML
    void abrirInformacion(ActionEvent event) {

    }

    @FXML
    void abrirVentanaClientes(ActionEvent event) {
        Controlador ventanaClientes = Controladores.get("/vistas/borrarCliente.fxml","Borrar Cliente",null);
        ventanaClientes.getEscenario().show();

    }

    @FXML
    void abrirVentanaSalir(ActionEvent event) {

    }

    @FXML
    void abrirVentanaTrabajos(ActionEvent event) {

    }

    @FXML
    void abrirVentanaVehiculos(ActionEvent event) {

    }

}