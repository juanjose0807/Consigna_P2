package Utils;

import Models.Habitacion;
import Models.Hotel;
import Models.Reserva;

import java.util.Scanner;
import java.util.ArrayList;

public class SistemaReservas {
    private Hotel hotel;
    private Scanner scanner;

    public SistemaReservas() {
        hotel = new Hotel();
        scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ HOTEL ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearReserva();
                    break;
                case 2:
                    consultarDisponibilidad();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void crearReserva() {
        System.out.print("Ingrese nombre del cliente: ");
        scanner.nextLine(); // limpiar buffer
        String nombre = scanner.nextLine();

        System.out.print("Seleccione tipo de habitación (Simple/Doble): ");
        String tipo = scanner.nextLine();

        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : hotel.getHabitaciones()) {
            if (h.getTipo().equalsIgnoreCase(tipo) && h.estaDisponible()) {
                disponibles.add(h);
            }
        }

        if (disponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles del tipo " + tipo + ".");
            return;
        }

        System.out.println("Habitaciones disponibles:");
        for (Habitacion h : disponibles) {
            System.out.println(" - Habitación " + h.getNumero());
        }

        System.out.print("Ingrese número de habitación a reservar: ");
        int numero = scanner.nextInt();

        for (Habitacion h : hotel.getHabitaciones()) {
            if (h.getNumero() == numero && h.estaDisponible()) {
                Reserva nueva = new Reserva(nombre, h);
                hotel.getReservas().add(nueva);
                System.out.println("Reserva realizada con éxito.");
                return;
            }
        }

        System.out.println("No se pudo reservar. Verifique el número.");
    }

    private void consultarDisponibilidad() {
        System.out.print("Ingrese número de habitación: ");
        int numero = scanner.nextInt();

        for (Habitacion h : hotel.getHabitaciones()) {
            if (h.getNumero() == numero) {
                if (h.estaDisponible()) {
                    System.out.println("La habitación " + numero + " (" + h.getTipo() + ") está disponible.");
                } else {
                    for (Reserva r : hotel.getReservas()) {
                        if (r.getNumeroHabitacion() == numero) {
                            System.out.println("La habitación " + numero + " está ocupada por " + r.getNombreCliente() + ".");
                            return;
                        }
                    }
                    System.out.println("La habitación está ocupada, pero no se encontró reserva.");
                }
                return;
            }
        }

        System.out.println("⚠️ Habitación no encontrada.");
    }
}
