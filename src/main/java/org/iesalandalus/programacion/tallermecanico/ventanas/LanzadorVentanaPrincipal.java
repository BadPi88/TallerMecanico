package org.iesalandalus.programacion.tallermecanico.ventanas;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("vista/vistaGrafica.fxml", "Taller Mecanico", null);
        ventanaPrincipal.getEscenario().show();


    }

    public void comenzar() {
        launch(LanzadorVentanaPrincipal.class);
    }
}
