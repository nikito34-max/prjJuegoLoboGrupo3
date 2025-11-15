package pkJuego;

import pkJuego.pkPersonaje.*;
import pkJuego.pkTransporte.*;
import java.util.Scanner;

public class AppJuego {

    private LadoA ladoA;
    private LadoB ladoB;
    private Barco barco;
    private Vikingo vikingo;
    private Lobo lobo;
    private Caperucita caperucita;
    private Uva uva;
    private Boolean juegoTerminado;

    public AppJuego() {
        ladoA = new LadoA();
        ladoB = new LadoB();
        lobo = new Lobo();
        caperucita = new Caperucita();
        uva = new Uva();
        vikingo = null;
        ladoA.agregar(lobo);
        ladoA.agregar(caperucita);
        ladoA.agregar(uva);
        barco = new Barco(ladoA);
        juegoTerminado = false;
    }

    public void IniciarJuego() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "Vikingo";
        }
        vikingo = new Vikingo(nombre);
        ladoA.agregar(vikingo);

        while (!juegoTerminado) {
            mostrarEstado();

            if (verificarVictoria()) {
                System.out.println("Has ganado el juego.");
                juegoTerminado = true;
                break;
            }

            if (ladoA.hayPeligro() || ladoB.hayPeligro()) {
                System.out.println("Alguien se comió a otro. Has perdido.");
                juegoTerminado = true;
                break;
            }

            System.out.println("Elige una opción:");
            System.out.println("1. Cruzar solo");
            System.out.println("2. Cruzar con Lobo");
            System.out.println("3. Cruzar con Caperucita");
            System.out.println("4. Cruzar con Uva");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            String entrada = sc.nextLine().trim();
            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }

            if (opcion == 5) {
                System.out.println("\n===JUEGO TERMINADO===.");
                juegoTerminado = true;
                break;
            }

            LadoDelRio origen = barco.getLadoActual();
            LadoDelRio destino = (origen instanceof LadoA) ? ladoB : ladoA;

            if (!origen.contiene(vikingo)) {
                System.out.println("El vikingo no está en este lado.");
                continue;
            }

            boolean movimientoValido = false;

            switch (opcion) {
                case 1:
                    movimientoValido = cruzarSolo(origen, destino);
                    break;
                case 2:
                    movimientoValido = cruzarConPersonaje(origen, destino, lobo);
                    break;
                case 3:
                    movimientoValido = cruzarConPersonaje(origen, destino, caperucita);
                    break;
                case 4:
                    movimientoValido = cruzarConPersonaje(origen, destino, uva);
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

            if (!movimientoValido) {
                System.out.println("Movimiento no válido.");
                continue;
            }

            if (ladoA.hayPeligro() || ladoB.hayPeligro()) {
                mostrarEstado();
                System.out.println("Alguien comió en el otro lado. Has perdido.");
                juegoTerminado = true;
            }
        }
        sc.close();
    }

    private boolean cruzarSolo(LadoDelRio origen, LadoDelRio destino) {
        vikingo.remar();
        origen.quitar(vikingo);
        barco.transportar(destino);
        destino.agregar(vikingo);
        return true;
    }

    private boolean cruzarConPersonaje(LadoDelRio origen, LadoDelRio destino, Personaje p) {
        if (!origen.contiene(p)) {
            System.out.println(p.getNombre() + " no está en este lado.");
            return false;
        }
        if (!barco.embarcar(p)) {
            System.out.println("No se pudo embarcar a " + p.getNombre() + ".");
            return false;
        }
        vikingo.remar();
        origen.quitar(vikingo);
        barco.transportar(destino);
        barco.desembarcar();
        destino.agregar(vikingo);
        return true;
    }

    private boolean verificarVictoria() {
        if (!ladoB.contiene(vikingo)) return false;
        if (!ladoB.contiene(lobo)) return false;
        if (!ladoB.contiene(caperucita)) return false;
        if (!ladoB.contiene(uva)) return false;
        if (!vikingo.isVivo()) return false;
        if (!lobo.isVivo()) return false;
        if (!caperucita.isVivo()) return false;
        if (!uva.isVivo()) return false;
        return true;
    }

    private void mostrarEstado() {
        System.out.println("------------------------------");
        System.out.println("Lado A:");
        for (Personaje p : ladoA.getPersonajes()) {
            System.out.println("- " + p.getNombre());
        }
        System.out.println("Lado B:");
        for (Personaje p : ladoB.getPersonajes()) {
            System.out.println("- " + p.getNombre());
        }
        System.out.println("El barco está en el lado " + barco.getLadoActual().getNombre());
        System.out.println("------------------------------");
    }

    public LadoA getLadoA() {
        return ladoA;
    }

    public void setLadoA(LadoA ladoA) {
        this.ladoA = ladoA;
    }

    public LadoB getLadoB() {
        return ladoB;
    }

    public void setLadoB(LadoB ladoB) {
        this.ladoB = ladoB;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Vikingo getVikingo() {
        return vikingo;
    }

    public void setVikingo(Vikingo vikingo) {
        this.vikingo = vikingo;
    }

    public Lobo getLobo() {
        return lobo;
    }

    public void setLobo(Lobo lobo) {
        this.lobo = lobo;
    }

    public Caperucita getCaperucita() {
        return caperucita;
    }

    public void setCaperucita(Caperucita caperucita) {
        this.caperucita = caperucita;
    }

    public Uva getUva() {
        return uva;
    }

    public void setUva(Uva uva) {
        this.uva = uva;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(Boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }
}
