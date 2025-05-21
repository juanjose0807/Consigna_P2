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

        ArrayList<Habitacion> disponibles = hotel.obtenerHabitacionesDisponiblesPorTipo(tipo);
        if (disponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles del tipo " + tipo + ".");
            return;
        }

        // Mostrar precio una sola vez
        if (!disponibles.isEmpty()) {
            double precio = disponibles.get(0).getPrecio(); // todas tienen el mismo precio
            System.out.println("Precio por noche para habitación tipo " + tipo + ": $" + precio);
        }

        // Mostrar solo los números de habitación
        System.out.println("Habitaciones disponibles:");
        for (Habitacion h : disponibles) {
            System.out.println(" - Habitación " + h.getNumero());
        }

        System.out.print("Ingrese número de habitación a reservar: ");
        int numero = scanner.nextInt();

        System.out.print("Ingrese número de noches a reservar: ");
        int noches = scanner.nextInt();

        boolean reservada = hotel.crearReserva(nombre, numero, noches);
        if (reservada) {
            Reserva reserva = hotel.obtenerReservaPorHabitacion(numero);
            System.out.println("Reserva realizada con éxito en habitación " + tipo + ".");
            System.out.println("Total a pagar por " + noches + " noches: $" + reserva.calcularTotal());
        } else {
            System.out.println("No se pudo realizar la reserva. Verifique el número ingresado.");
        }
    }

    private void consultarDisponibilidad() {
        System.out.print("Ingrese número de habitación: ");
        int numero = scanner.nextInt();
        String tipo = hotel.obtenerTipoHabitacion(numero);

        if (tipo.equals("Desconocido")) {
            System.out.println("La habitación no existe.");
            return;
        }

        if (hotel.consultarDisponibilidad(numero)) {
            System.out.println("La habitación " + numero + " (" + tipo + ") está disponible.");
        } else {
            Reserva reserva = hotel.obtenerReservaPorHabitacion(numero);
            if (reserva != null) {
                System.out.println("La habitación " + numero + " (" + tipo + ") está ocupada.");
                System.out.println("Cliente hospedado: " + reserva.getNombreCliente());
                System.out.println("Duración: " + reserva.getNoches() + " noches");
                System.out.println("Total a pagar: $" + reserva.calcularTotal());
            } else {
                System.out.println("La habitación está ocupada pero no se encontró reserva.");
            }
        }
    }
}
