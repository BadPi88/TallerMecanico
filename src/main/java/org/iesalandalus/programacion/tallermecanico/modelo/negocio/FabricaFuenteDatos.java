package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FabricaFuenteDatos {
    MEMORIA {
        @Override
        public IFuenteDatosMemoria crear() {
            return new FuenteDatosMemoria();
        }
    };

    public abstract IFuenteDatosMemoria crear();
}
