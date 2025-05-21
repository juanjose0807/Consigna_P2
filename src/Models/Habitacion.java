package Models;

public class Habitacion {
    private int numero;
    private String tipo; // "Simple" o "Doble"
    private boolean disponible;
    private double precio; // precio por noche

    public Habitacion(int numero, String tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void reservar() {
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }

    public double getPrecio() {
        return precio;
    }
}
