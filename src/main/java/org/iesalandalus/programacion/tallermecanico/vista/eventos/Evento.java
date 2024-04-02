package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE("Insertar cliente", 11),
    BUSCAR_CLIENTE("Buscar cliente", 12),
    BORRAR_CLIENTE("Borra cliente", 13),
    LISTAR_CLIENTES("Listar clientes", 14),
    MODIFICAR_CLIENTE("Modificar Clientes", 15),
    INSERTAR_VEHICULO("Insertar vehiculo", 21),
    BUSCAR_VEHICULO("Buscar vehiculo", 22),
    BORRAR_VEHICULO("Insertar vehiculo", 23),
    LISTAR_VEHICULOS("Listar vehículos", 24),
    INSERTAR_REVISION("Insertar revision", 31),
    INSERTAR_MECANICO("Insertar mecánico", 30),
    BUSCAR_TRABAJO("Buscar trabajo", 32),
    BORRAR_TRABAJO("Borrar trabajo", 33),
    LISTAR_TRABAJOS("Listar trabajos", 34),
    LISTAR_TRABAJOS_CLIENTES("Listar trabajos por cliente", 35),
    LISTAR_TRABAJOS_VEHICULOS("Listar trabajos por vehículo", 36),
    ANADIR_HORAS_TRABAJO("Añadir horas trabajo ", 37),
    ANADIR_PRECIO_MATERIAL_TRABAJO("Añadir precio material trabajo ", 38),
    CERRAR_TRABAJO("Cerrar el trabajo", 39),
    SALIR("Salir", 0);
    private final String texto;
    private final Integer codigo;
    private static final Map<Integer, Evento> eventos = new HashMap<>();

    //BLOQUES ESTATICOS para inicicializar
    static {
        for (Evento evento : values()) {
            eventos.put(evento.codigo, evento);
        }
    }

    Evento(String texto, Integer codigo) {
        this.texto = texto;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static boolean esValido(int codigo) {
        return eventos.containsKey(codigo);
    }

    public static Evento get(int codigo) {
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("Código no válido.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return texto;
    }
}
