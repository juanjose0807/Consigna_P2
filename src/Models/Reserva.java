package Models;

public class Reserva {
    private String nombreCliente;
    private Habitacion habitacion;
    private int noches;

    public Reserva(String nombreCliente, Habitacion habitacion, int noches) {
        this.nombreCliente = nombreCliente;
        this.habitacion = habitacion;
        this.noches = noches;
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

    public int getNoches() {
        return noches;
    }

    public double calcularTotal() {
        return noches * habitacion.getPrecio();
    }
}
