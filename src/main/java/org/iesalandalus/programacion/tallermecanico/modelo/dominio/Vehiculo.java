package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = "[A-Z][a-z]+([ -]?[A-Z][a-z]+)?|[A-Z]+";
    private static final String ER_MATRICULA = "^\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}";

    public Vehiculo {
        validarMarca(marca);
        validaModelo(modelo);
        validarMatricula(matricula);
    }

    public void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
        if (marca.isBlank()) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }

    }

    public void validaModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    public void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula) {
        return new Vehiculo("Renault", "Megane", matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
