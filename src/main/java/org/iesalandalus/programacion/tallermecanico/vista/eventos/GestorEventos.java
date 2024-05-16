package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    //
    private final Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);

    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos, "Debes gestionar un evento válido.");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor  de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "Te tienes que suscribir a un evento.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            if (usuarios == null) {
                usuarios = new ArrayList<>();
                receptores.put(evento, usuarios);
            }
            usuarios.add(receptor);
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "No puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            usuarios.remove(receptor);
        }
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "No se puede notificar un evento nulo");
        List<ReceptorEventos> usuarios = receptores.get(evento);
        for (ReceptorEventos receptorEventos : usuarios) {
            receptorEventos.actualizar(evento);
        }
    }
}
