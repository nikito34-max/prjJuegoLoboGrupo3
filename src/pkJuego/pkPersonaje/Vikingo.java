package pkJuego.pkPersonaje;

public class Vikingo extends Personaje {

    public Vikingo(String nombre) {
        super(nombre);
    }
    
    public void remar() {
        System.out.println(getNombre() + " está remando...");
    }
}

