package org.iesalandalus.programacion.tallermecanico.ventanas.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.grafico.VistaGrafica;

public class BuscarCliente extends Controlador {
    static Cliente cliente;

    @FXML
    private TextField tfDni;

    @FXML
    void ratonCancelar(MouseEvent event) {
        getEscenario().close();

    }

    @FXML
    void ratonConfirmar(MouseEvent event) {
        String dni = tfDni.getText();

        if (dni != null && !dni.isEmpty()) {
            Cliente cliente = Cliente.get(dni);
            Cliente clienteEncontrado = VistaGrafica.getInstancia().leerClienteDni();
            if (clienteEncontrado != null) {
                Dialogos.mostrarDialogoInformacion("Cliente Encontrado", clienteEncontrado.toString(), null);
            } else {
                Dialogos.mostrarDialogoAdvertencia("Cliente No Encontrado", "No se encontró un cliente con el DNI proporcionado.", null);
            }
        } else {
            Dialogos.mostrarDialogoError("Error", "Debe proporcionar un DNI.",null);
        }
    }

}

