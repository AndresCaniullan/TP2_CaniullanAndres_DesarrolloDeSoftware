// package src.app;

// import src.models.Cliente;
// import src.models.Paquete;
// import src.models.Sucursal;
// import src.models.tiposEnvio.EnvioExpres;
// import java.util.List;
// import java.util.ArrayList;

// public class Main {
//     public static void main(String[] args) {

//         // ===== Cliente =====
//         Cliente cliente = new Cliente("Andrés", "Caniullan", "Mitre 123", "+54-298433445", "carlos@mail.com", "20-12345678-9");

//         // ===== Paquetes =====
//         Paquete p1 = new Paquete(1, "Libro", 2.0);
//         Paquete p2 = new Paquete(2, "Electrónica", 11.0); // supera 10kg -> recargo
//         List<Paquete> listaPaquetes = new ArrayList<>();
//         listaPaquetes.add(p1);
//         listaPaquetes.add(p2);

//         // ===== Envío =====
//         EnvioExpres envio = new EnvioExpres(listaPaquetes);
//         cliente.agregarEnvio(envio);

//         System.out.println("=== Estado inicial ===");
//         envio.mostrarResumen();

//         // ===== Sucursales del recorrido =====
//         Sucursal viedma = new Sucursal(1, "Viedma", "Av. Perón 100");
//         Sucursal bahiaBlanca = new Sucursal(2, "Bahía Blanca", "San Martín 200");
//         Sucursal buenosAires = new Sucursal(3, "Buenos Aires", "Corrientes 300");

//         // ===== Recorrido =====
//         System.out.println("\n=== Recorrido ===");

//         viedma.recibirEnvio(envio);      // EN_PREPARACION -> EN_ALMACEN
//         viedma.despacharEnvio(envio);    // EN_ALMACEN -> EN_TRANSITO

//         bahiaBlanca.recibirEnvio(envio); // EN_TRANSITO -> EN_ALMACEN
//         bahiaBlanca.despacharEnvio(envio); // EN_ALMACEN -> EN_TRANSITO

//         buenosAires.entregarEnvio(envio); // EN_TRANSITO -> ENTREGADO (destino final)

//         System.out.println("\n=== Estado final ===");
//         envio.mostrarResumen();

//         System.out.println("\n=== Historial del envío ===");
//         envio.mostrarHistorial();

//         // ===== Probar que las validaciones del Nivel 3 funcionan =====
//         System.out.println("\n=== Probando una operación inválida ===");
//         try {
//             // el envío ya fue entregado, no debería poder despacharse de nuevo
//             viedma.despacharEnvio(envio);
//         } catch (IllegalStateException e) {
//             System.out.println("Operación rechazada correctamente: " + e.getMessage());
//         }

//         // ===== Consultas del cliente =====
//         System.out.println("\n=== Envíos del cliente " + cliente.getNombre() + " ===");
//         System.out.println("Cantidad de envíos: " + cliente.cantidadEnvios());
//         cliente.mostrarEnvios();
//     }
// }