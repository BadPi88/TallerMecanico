package org.iesalandalus.programacion.tallermecanico.vista.grafico;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.ventanas.LanzadorVentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VistaGrafica implements Vista {
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());
    private Controlador ventanaPrincipal;
    private static VistaGrafica instancia;

    public static VistaGrafica getInstancia() {
        if (instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    @Override
    public LocalDate leerFechaCierre() {
        DatePicker datePicker = new DatePicker();
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Fecha de Cierre");
        dialog.getDialogPane().setContent(datePicker);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.setResultConverter(button -> button == ButtonType.OK ? datePicker.getValue() : null);
        Optional<LocalDate> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    public void setVentanaPrincipal(Controlador ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @Override
    public void comenzar() {
        LanzadorVentanaPrincipal.comenzar();

    }

    @Override
    public void terminar() {
        System.out.printf("Hasta luegoo!!");

    }

    @Override
    public Cliente leerCliente() {
        Dialog<Cliente> dialog = new Dialog<>();
        dialog.setTitle("Nuevo Cliente");

        GridPane grid = new GridPane();
        TextField dniField = new TextField();
        dniField.setPromptText("DNI");
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre");
        TextField telefonoField = new TextField();
        telefonoField.setPromptText("Teléfono");

        grid.add(new Label("DNI:"), 0, 0);
        grid.add(dniField, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(nombreField, 1, 1);
        grid.add(new Label("Teléfono:"), 0, 2);
        grid.add(telefonoField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return new Cliente(dniField.getText(), nombreField.getText(), telefonoField.getText());
            }
            return null;
        });

        Optional<Cliente> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public Cliente leerClienteDni() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Cliente");
        dialog.setHeaderText("Introduce el DNI del cliente:");
        //falta que se lea el texto
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo Nombre");
        dialog.setHeaderText("Introduce el nuevo nombre:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public String leerNuevoTelefono() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo Teléfono");
        dialog.setHeaderText("Introduce el nuevo teléfono:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public Vehiculo leerVehiculo() {
        Dialog<Vehiculo> dialog = new Dialog<>();
        dialog.setTitle("Nuevo Vehiculo");

        GridPane grid = new GridPane();
        TextField matriculaField = new TextField();
        matriculaField.setPromptText("Matricula");
        TextField marcaField = new TextField();
        marcaField.setPromptText("Marca");
        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo");

        grid.add(new Label("Matricula:"), 0, 0);
        grid.add(matriculaField, 1, 0);
        grid.add(new Label("Marca:"), 0, 1);
        grid.add(marcaField, 1, 1);
        grid.add(new Label("Modelo:"), 0, 2);
        grid.add(modeloField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return new Vehiculo(matriculaField.getText(), marcaField.getText(), modeloField.getText());
            }
            return null;
        });

        Optional<Vehiculo> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public Vehiculo leerMatriculaVehiculo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Vehiculo");
        dialog.setHeaderText("Introduce la matrícula del vehiculo:");
        Optional<String> result = dialog.showAndWait();
        return null;
    }

    @Override
    public Trabajo leerRevision() {
        return leerTrabajoGenerico("Nueva Revision");
    }

    @Override
    public Trabajo leerMecanico() {
        return leerTrabajoGenerico("Nuevo Trabajo de Mecánico");
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return leerTrabajoGenerico("Nuevo Trabajo para Vehículo");
    }

    private Trabajo leerTrabajoGenerico(String titulo) {
        Dialog<Trabajo> dialog = new Dialog<>();
        dialog.setTitle(titulo);

        GridPane grid = new GridPane();
        TextField descripcionField = new TextField();
        descripcionField.setPromptText("Descripción");
        TextField horasField = new TextField();
        horasField.setPromptText("Horas");

        grid.add(new Label("Descripción:"), 0, 0);
        grid.add(descripcionField, 1, 0);
        grid.add(new Label("Horas:"), 0, 1);
        grid.add(horasField, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<Trabajo> result = dialog.showAndWait();
        return result.orElse(null);
    }

    @Override
    public int leerHoras() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Horas de Trabajo");
        dialog.setHeaderText("Introduce las horas de trabajo:");
        Optional<String> result = dialog.showAndWait();
        return result.map(Integer::parseInt).orElse(0);
    }

    @Override
    public float leerPrecioMaterial() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Precio del Material");
        dialog.setHeaderText("Introduce el precio del material:");
        Optional<String> result = dialog.showAndWait();
        return result.map(Float::parseFloat).orElse(0f);
    }

    @Override
    public void notificarEvento(Evento evento, String texto, boolean existe) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación de Evento");
        alert.setHeaderText(evento.toString());
        alert.setContentText(texto + (existe ? " (existe)" : " (no existe)"));
        alert.showAndWait();
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Cliente");
        alert.setHeaderText("Información del Cliente");
        alert.setContentText(cliente.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarVehiculos(Vehiculo vehiculo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Vehículo");
        alert.setHeaderText("Información del Vehículo");
        alert.setContentText(vehiculo.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Trabajo");
        alert.setHeaderText("Información del Trabajo");
        alert.setContentText(trabajo.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Clientes");
        alert.setHeaderText("Lista de Clientes");
        StringBuilder content = new StringBuilder();
        for (Cliente cliente : clientes) {
            content.append(cliente.toString()).append("\n");
        }
        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Vehículos");
        alert.setHeaderText("Lista de Vehículos");
        StringBuilder content = new StringBuilder();
        for (Vehiculo vehiculo : vehiculos) {
            content.append(vehiculo.toString()).append("\n");
        }
        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mostrar Trabajos");
        alert.setHeaderText("Lista de Trabajos");
        StringBuilder content = new StringBuilder();
        for (Trabajo trabajo : trabajos) {
            content.append(trabajo.toString()).append("\n");
        }
        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estadísticas Mensuales");
        alert.setHeaderText("Estadísticas por Tipo de Trabajo");
        StringBuilder content = new StringBuilder();
        for (Map.Entry<TipoTrabajo, Integer> entry : estadisticas.entrySet()) {
            content.append(entry.getKey().toString()).append(": ").append(entry.getValue()).append("\n");
        }
        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    @Override
    public LocalDate leerMes() {
        DatePicker datePicker = new DatePicker();
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Seleccionar Mes");
        dialog.getDialogPane().setContent(datePicker);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.setResultConverter(button -> button == ButtonType.OK ? datePicker.getValue().withDayOfMonth(1) : null);
        Optional<LocalDate> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
