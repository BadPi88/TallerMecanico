package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

import java.io.IOException;

public class VistaClientes extends Controlador {
    private VistaGrafica vistaGrafica;
    private static Scene escenaClientes;

    @FXML
    private VBox raiz;

    public static void setEscenaClientes(Scene escena){
        if (escenaClientes == null){
            escenaClientes = escena;
        }

    }
    public Scene getEscenaPrincipal(){
        return escenaClientes;
    }
    @FXML
    void btAtras(ActionEvent event) {
        // Obtener el Stage actual a través del evento
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana actual
        stageActual.close();
    }

    @FXML
    void btBorrarCliente(ActionEvent event) {
        try {
            // Cargar el archivo FXML para la ventana borrarCliente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/borrarCliente.fxml"));
            Parent root = loader.load();

            // Crear y mostrar la ventana
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Borrar Cliente");
            // Hace que la ventana sea modal lo que bliga a que el usuario interactue antes de cerrar importante para asegurarse que cancela o confirma
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Muestra la ventana y espera hasta que se cierre

        } catch (IOException e) {
            System.out.printf("ERROR");
        }
    }

    @FXML
    void btBuscarCliente(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/buscarCliente.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Borrar Cliente");
            stage.show();

        } catch (IOException e) {
            System.out.printf("ERROR");
        }
    }

    @FXML
    void btInsertarCliente(ActionEvent event) {

    }

    @FXML
    void btListarClientes(ActionEvent event) {

    }

}

