package org.iesalandalus.programacion.tallermecanico.ventanas;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

public class LanzadorVentanaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/vistaGrafica.fxml", "Taller Mecanico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();


    }

    public static void comenzar() {
        launch(LanzadorVentanaPrincipal.class);
    }
    void salir(WindowEvent e,Stage escenario){
        if(Dialogos.mostrarDialogoConfirmacion("Salir","Estas seguro?",escenario)){
            escenario.close();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
        }else {
            e.consume();
        }
    }
}
