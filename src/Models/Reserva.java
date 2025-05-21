package Models;

public class Reserva {
    private String nombreCliente;
    private Habitacion habitacion;

    public Reserva(String nombreCliente, Habitacion habitacion) {
        this.nombreCliente = nombreCliente;
        this.habitacion = habitacion;
        this.habitacion.reservar();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroHabitacion() {
        return habitacion.getNumero();
    }

    public String getTipoHabitacion() {
        return habitacion.getTipo();
    }
}
