package src.models;
import src.services.CalculadoraCostos;
import src.enums.TipoMovimiento;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Envio {
    // ===================== Atributos =====================
    private List<Paquete> paquetes;
    private double costo;
    private double recargo;
    private List<Movimiento> historial;
    // ===================== Constructor =====================
    public Envio(List<Paquete> paquetes) {
        if (paquetes.size() == 0 || paquetes.size() > 3) {
            throw new IllegalArgumentException("Un envío debe tener entre 1 y 3 paquetes.");
        }
        this.paquetes = paquetes;
        this.costo = CalculadoraCostos.calcularCosto(paquetes);
        this.recargo = CalculadoraCostos.aplicarRecargo(paquetes);
        this.historial = new ArrayList<>();
    }
    // ===================== Getters =====================
    public double getCosto() {
        return costo;
    }
    public double getRecargo() {
        return recargo;
    }
    public List<Paquete> getPaquetes() {
        return paquetes;
    }
    public List<Movimiento> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
    public Movimiento getUltimoMovimiento() {
        if (historial.isEmpty()) {
            return null;
        }
        return historial.get(historial.size() - 1);
    }
    // ===================== Cambiar el estado del envío ======================
   public void despachar(Sucursal sucursal) {
    for (Paquete p : paquetes) {
        if (!p.puedeEnviar()) {
            throw new IllegalStateException(
                "No se puede despachar: el paquete " + p.getId() + " está en estado " + p.getEstado());
        }
    }
    for (Paquete p : paquetes) {
        p.enviar();
    }
    registrarMovimiento(sucursal, TipoMovimiento.DESPACHADO);
    }
    public void recibir(Sucursal sucursal) {
        for (Paquete p : paquetes) {
            if (!p.puedeAlmacenar()) {
                throw new IllegalStateException(
                    "No se puede recibir: el paquete " + p.getId() + " está en estado " + p.getEstado());
            }
        }
        for (Paquete p : paquetes) {
            p.almacenar();
        }
        registrarMovimiento(sucursal, TipoMovimiento.RECIBIDO);
    }
    public void entregar(Sucursal sucursal) {
        for (Paquete p : paquetes) {
            if (!p.puedeEntregar()) {
                throw new IllegalStateException(
                    "No se puede entregar: el paquete " + p.getId() + " está en estado " + p.getEstado());
            }
        }
        for (Paquete p : paquetes) {
            p.entregar();
        }
        registrarMovimiento(sucursal, TipoMovimiento.ENTREGADO);
    }
    // ===================== Registrar un movimiento en el historial del envío =====================
    // Método privado para registrar un movimiento en el historial del envío
    private void registrarMovimiento(Sucursal sucursal, TipoMovimiento tipoMovimiento) {
        historial.add(new Movimiento(sucursal, tipoMovimiento, java.time.LocalDateTime.now()));
    }
    // Método para mostrar el historial de movimientos del envío
    public void mostrarHistorial() {
        System.out.println("Historial del envío:");
        for (Movimiento m : historial) {
            System.out.println("\t" + m);
        }
    }
    // ===================== Mostrar resumen del envío =====================
    public void mostrarResumen() {
        System.out.println("Resumen del Envío:");
        System.out.println("\t Envío con " + paquetes.size() + " paquetes.");
        System.out.println("\t Costo del producto: $" + costo);
        System.out.println("\t Costo de recargo por peso excesivo: $" + recargo);
        System.out.println("\t Costo total: $" + (costo + recargo));
        System.out.println("Detalles de los paquetes:");
        for (Paquete p : paquetes) {
            p.mostrarInformacion();
        }
    }
}