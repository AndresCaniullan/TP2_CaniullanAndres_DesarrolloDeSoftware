package src.services;
import java.util.List;
import src.models.Paquete;

public class CalculadoraCostos {
    // ===================== Calcular el costo total del envío =====================
    public static double calcularCosto(List<Paquete> paquetes) {
        double totalPeso = 0;
        for (Paquete p : paquetes) {
            totalPeso += p.getPeso();
        }
        // Regla básica: $100 por kg
        double costoBase = totalPeso * 100;
        // Aplicar recargos internos
        double recargo = aplicarRecargo(paquetes);
        return costoBase + recargo;
    }
    // ===================== Aplicar recargos internos =====================
    public static double aplicarRecargo(List<Paquete> paquetes) {
        double recargo = 0;
        // Ejemplo: si algún paquete pesa más de 10 kg, se cobra un extra
        for (Paquete p : paquetes) {
            if (p.getPeso() > 10) {
                recargo += 500; // recargo fijo
            }
        }
        return recargo;
    }
}
