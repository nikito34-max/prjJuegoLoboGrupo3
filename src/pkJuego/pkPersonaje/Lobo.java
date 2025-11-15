package pkJuego.pkPersonaje;

public class Lobo extends Personaje {

    public Lobo() {
        super("Lobo");
    }

    public boolean comer(Caperucita cap) {
        cap.morir();
        return true;
    }
}

