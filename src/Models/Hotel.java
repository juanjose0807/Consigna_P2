package Models;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;

    public Hotel() {
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            habitaciones.add(new Habitacion(i, "Simple"));
        }
        for (int i = 6; i <= 10; i++) {
            habitaciones.add(new Habitacion(i, "Doble"));
        }
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}
