package Models;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;

    public Hotel() {
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();

        // Precios sugeridos (puedes cambiar)
        double precioSimple = 50.0;
        double precioDoble = 80.0;

        // Crear habitaciones simples (1–5) y dobles (6–10) con precio
        for (int i = 1; i <= 5; i++) {
            habitaciones.add(new Habitacion(i, "Simple", precioSimple));
        }
        for (int i = 6; i <= 10; i++) {
            habitaciones.add(new Habitacion(i, "Doble", precioDoble));
        }
    }

    public boolean crearReserva(String nombreCliente, int numeroHabitacion, int noches) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numeroHabitacion && h.estaDisponible()) {
                reservas.add(new Reserva(nombreCliente, h, noches));
                return true;
            }
        }
        return false;
    }

    public boolean consultarDisponibilidad(int numeroHabitacion) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numeroHabitacion) {
                return h.estaDisponible();
            }
        }
        return false;
    }

    public Reserva obtenerReservaPorHabitacion(int numeroHabitacion) {
        for (Reserva r : reservas) {
            if (r.getNumeroHabitacion() == numeroHabitacion) {
                return r;
            }
        }
        return null;
    }

    public String obtenerTipoHabitacion(int numeroHabitacion) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numeroHabitacion) {
                return h.getTipo();
            }
        }
        return "Desconocido";
    }

    public ArrayList<Habitacion> obtenerHabitacionesDisponiblesPorTipo(String tipo) {
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo) && h.estaDisponible()) {
                disponibles.add(h);
            }
        }
        return disponibles;
    }
}
