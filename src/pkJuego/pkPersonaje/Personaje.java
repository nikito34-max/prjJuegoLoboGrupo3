package pkJuego.pkPersonaje;

public abstract class Personaje {

    protected String nombre;
    protected boolean estaVivo;

    public Personaje(String nombre) {
        this.setNombre(nombre);
        this.setVivo(true);
    }

    public void morir() {
        this.setVivo(false);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVivo() {
        return estaVivo;
    }

    public void setVivo(boolean estado) {
        this.estaVivo = estado;
    }
}


