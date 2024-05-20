package org.iesalandalus.programacion.tallermecanico.ventanas;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/vistaPrincipal.fxml", "Taller Mecanico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();

        stage.setTitle("Taller mecanico");


    }

    public static void comenzar() {
        launch(LanzadorVentanaPrincipal.class);
    }

    public static void salir(WindowEvent e,Stage escenario){
        if(Dialogos.mostrarDialogoConfirmacion("Salir","Estas seguro?",escenario)){
            escenario.close();
        }else {
            e.consume();
        }
    }
}
