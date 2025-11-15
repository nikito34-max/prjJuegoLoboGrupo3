package pkJuego.pkTransporte;

import pkJuego.pkPersonaje.Personaje;

public class Barco extends MedioTransporte {

    private LadoDelRio ladoActual;
    private Personaje pasajero;

    public Barco(LadoDelRio ladoInicial) {
        this.ladoActual = ladoInicial;
        this.pasajero = null;
    }

    public boolean embarcar(Personaje p) {
        if (pasajero != null) return false;
        if (!ladoActual.contiene(p)) return false;

        ladoActual.quitar(p);
        pasajero = p;
        return true;
    }

    public Personaje desembarcar() {
        if (pasajero == null) return null;

        ladoActual.agregar(pasajero);
        Personaje temp = pasajero;
        pasajero = null;
        return temp;
    }

    public void transportar(LadoDelRio destino) {
        ladoActual = destino;
    }

    public LadoDelRio getLadoActual() {
        return ladoActual;
    }

    public void setLadoActual(LadoDelRio ladoActual) {
        this.ladoActual = ladoActual;
    }

    public Personaje getPasajero() {
        return pasajero;
    }

    public void setPasajero(Personaje pasajero) {
        this.pasajero = pasajero;
    }
}

