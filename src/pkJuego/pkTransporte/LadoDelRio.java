package pkJuego.pkTransporte;

import java.util.*;
import pkJuego.pkPersonaje.*;

public abstract class LadoDelRio {

    protected String nombre;
    protected List<Personaje> personajes;

    public LadoDelRio(String nombre) {
        this.nombre = nombre;
        personajes = new ArrayList<>();
    }

    public void agregar(Personaje p) {
        personajes.add(p);
    }

    public void quitar(Personaje p) {
        personajes.remove(p);
    }

    public boolean contiene(Personaje p) {
        return personajes.contains(p);
    }

    public boolean hayPeligro() {
        boolean hayLobo = false;
        boolean hayCaperucita = false;
        boolean hayUva = false;
        boolean hayVikingo = false;

        for (Personaje p : personajes) {
            if (p instanceof Lobo && p.isVivo()) hayLobo = true;
            if (p instanceof Caperucita && p.isVivo()) hayCaperucita = true;
            if (p instanceof Uva && p.isVivo()) hayUva = true;
            if (p instanceof Vikingo && p.isVivo()) hayVikingo = true;
        }

        if (!hayVikingo && hayLobo && hayCaperucita) {
            Lobo l = null;
            Caperucita c = null;
            for (Personaje p : personajes) {
                if (p instanceof Lobo) l = (Lobo) p;
                if (p instanceof Caperucita) c = (Caperucita) p;
            }
            l.comer(c);
            return true;
        }

        if (!hayVikingo && hayCaperucita && hayUva) {
            Caperucita c = null;
            Uva u = null;
            for (Personaje p : personajes) {
                if (p instanceof Caperucita) c = (Caperucita) p;
                if (p instanceof Uva) u = (Uva) p;
            }
            c.comer(u);
            return true;
        }

        return false;
    }


    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}
