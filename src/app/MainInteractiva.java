package src.app;

import src.models.Cliente;
import src.models.Paquete;
import src.models.Sucursal;
import src.models.tiposEnvio.EnvioExpres;
import src.models.Envio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * - Menú interactivo por opciones (Reconocimiento vs. Recuerdo).
 * - Manejo amigable de errores y feedback inmediato al usuario.
 * - Formato claro de salida en consola.
 */
public class MainInteractiva {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ===== Datos de prueba preconfigurados =====
        Cliente cliente = new Cliente("Andrés", "Caniullan", "Mitre 123", "+54-298433445", "carlos@mail.com", "20-12345678-9");
        
        List<Paquete> paquetes = new ArrayList<>();
        paquetes.add(new Paquete(1, "Libro de Programación", 2.0));
        paquetes.add(new Paquete(2, "Monitor LED 24''", 11.0)); // Incluye recargo por peso (>10kg)

        Envio envio = new EnvioExpres(paquetes);
        cliente.agregarEnvio(envio);

        // Sucursales del recorrido
        Sucursal sucursalViedma = new Sucursal(1, "Viedma", "Av. Perón 100");
        Sucursal sucursalBahia = new Sucursal(2, "Bahía Blanca", "San Martín 200");

        boolean salir = false;

        System.out.println("==================================================");
        System.out.println("   SISTEMA DE GESTIÓN DE ENVÍOS Y DISTRIBUCIÓN   ");
        System.out.println("==================================================");
        System.out.println("Bienvenido/a, operador/a. Cliente activo: " + cliente.getNombre() + " " + cliente.getApellido());

        while (!salir) {
            // Menú basado en Reconocimiento: Opciones claras y visibles
            System.out.println("\n--------------------------------------------------");
            System.out.println("MENÚ DE OPERACIONES:");
            System.out.println("1. Registrar Recepción en Sucursal Viedma");
            System.out.println("2. Registrar Despacho desde Sucursal Viedma");
            System.out.println("3. Registrar Recepción en Sucursal Bahía Blanca");
            System.out.println("4. Registrar Despacho desde Sucursal Bahía Blanca");
            System.out.println("5. Consultar Resumen y Estado del Envío");
            System.out.println("6. Consultar Historial Completo de Movimientos");
            System.out.println("7. Consultar Sucursales Recorridas por el Envío");
            System.out.println("8. Consultar Último Movimiento del Envío");
            System.out.println("0. Salir del sistema");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese el número de la opción deseada: ");

            String opcion = scanner.nextLine().trim();

            System.out.println(); // Espaciado de legibilidad

            switch (opcion) {
                case "1":
                    ejecutarOperacion("Recepción en Viedma", () -> sucursalViedma.recibirEnvio(envio));
                    break;
                case "2":
                    ejecutarOperacion("Despacho desde Viedma", () -> sucursalViedma.despacharEnvio(envio));
                    break;
                case "3":
                    ejecutarOperacion("Recepción en Bahía Blanca", () -> sucursalBahia.recibirEnvio(envio));
                    break;
                case "4":
                    ejecutarOperacion("Despacho desde Bahía Blanca", () -> sucursalBahia.despacharEnvio(envio));
                    break;
               case "5":
                    System.out.println("=== RESUMEN ACTUAL DEL ENVÍO ===");
                    // Llamamos al nuevo método que devuelve el String y lo imprimimos
                    System.out.println(envio.generarResumen()); 
                    break;
                case "6":
                    System.out.println("=== HISTORIAL DE MOVIMIENTOS ===");
                    // Obtenemos la lista de movimientos y el Main se encarga de imprimirla
                    List<src.models.Movimiento> historial = envio.getHistorial();
                    if (historial.isEmpty()) {
                        System.out.println(" No hay movimientos registrados.");
                    } else {
                        for (src.models.Movimiento m : historial) {
                            System.out.println("\t> " + m.toString());
                        }
                    }
                    break;
                case "7":
                    System.out.println("=== SUCURSALES RECORRIDAS ===");
                    for (Sucursal s : envio.getSucursalesRecorridas()) {
                        System.out.println("\t> N°" + s.getIdSucursal() + " - " + s.getCiudad());
                    }
                    break;
                case "8":
                    System.out.println("=== ÚLTIMO MOVIMIENTO ===");
                    try {
                        System.out.println("\t> " + envio.getUltimoMovimiento());
                    } catch (IllegalStateException e) {
                        System.out.println(" [!] " + e.getMessage());
                    }
                    break;
                case "0":
                    salir = true;
                    System.out.println("¡Gracias por utilizar el Sistema de Gestión de Envíos!");
                    break;
                default:
                    // Feedback claro ante entradas inválidas
                    System.out.println(" [!] Opción no válida. Por favor, ingrese un número del 0 al 7.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Método auxiliar para ejecutar acciones operativas garantizando 
     * un feedback uniforme y un manejo controlado de excepciones.
     */
    private static void ejecutarOperacion(String nombreOperacion, Runnable accion) {
        try {
            accion.run();
            System.out.println(" [✓] ÉXITO: La operación '" + nombreOperacion + "' se completó correctamente.");
        } catch (IllegalStateException e) {
            System.out.println(" [X] OPERACIÓN RECHAZADA: " + e.getMessage());
            System.out.println("     Sugerencia: Verifique el estado actual del envío antes de reintentar.");
        } catch (Exception e) {
            System.out.println(" [!] ERROR INESPERADO: " + e.getMessage());
        }
    }
}