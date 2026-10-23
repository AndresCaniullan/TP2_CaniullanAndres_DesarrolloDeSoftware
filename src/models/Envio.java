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
        this.paquetes = new ArrayList<>(paquetes);
        this.costo = CalculadoraCostos.calcularCosto(this.paquetes);
        this.recargo = CalculadoraCostos.aplicarRecargo(this.paquetes);
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
    return new ArrayList<>(paquetes);
    }
    public List<Movimiento> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    // ===================== Obtener el último movimiento del envío =====================
   public Movimiento getUltimoMovimiento() {
    if (historial.isEmpty()) {
        throw new IllegalStateException("El envío todavía no registra movimientos.");
    }
    return historial.get(historial.size() - 1);
}
    // ===================== Consultar las sucursales recorridas por el envío =====================
public List<Sucursal> getSucursalesRecorridas() {
    List<Sucursal> recorridas = new ArrayList<>();
    for (Movimiento m : historial) {
        if (!recorridas.contains(m.getSucursal())) {
            recorridas.add(m.getSucursal());
        }
    }
    return recorridas;
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
    

    // ===================== Obtener resumen del envío (NUEVO) =====================
    public String generarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Envío:\n");
        resumen.append("\t Envío con ").append(paquetes.size()).append(" paquetes.\n");
        resumen.append("\t Costo del producto: $").append(costo).append("\n");
        resumen.append("\t Costo de recargo por peso excesivo: $").append(recargo).append("\n");
        resumen.append("\t Costo total: $").append(costo + recargo).append("\n");
        resumen.append("Detalles de los paquetes:\n");
        
        for (Paquete p : paquetes) {
            resumen.append("\t").append(p.toString()).append("\n"); 
        }
        return resumen.toString();
    }





}
