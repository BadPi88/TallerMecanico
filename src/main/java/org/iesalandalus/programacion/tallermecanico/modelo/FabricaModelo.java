package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatosMemoria;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FabricaModelo {
    CASCADA {
        public Modelo crear(FabricaFuenteDatos fabricaFuenteDatos) {
            return new ModeloCascada(fabricaFuenteDatos);
        }
    };


    public abstract Modelo crear(FabricaFuenteDatos fabricaFuenteDatos);
}
