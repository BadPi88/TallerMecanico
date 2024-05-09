package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {

    MECANICO("MECANICO"),
    REVISION("REVISION");
     public String nombre;

    TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo) {
        TipoTrabajo tipoTrabajo;
        if (trabajo instanceof Revision) {
            tipoTrabajo = REVISION;
        } else {
            tipoTrabajo = MECANICO;
        }
        return tipoTrabajo;

    }

}
