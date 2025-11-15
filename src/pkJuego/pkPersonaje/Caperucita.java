package pkJuego.pkPersonaje;

public class Caperucita extends Personaje {

    public Caperucita() {
        super("Caperucita");
    }

    public boolean comer(Uva uva) {
        uva.morir();
        return true;
    }
}
