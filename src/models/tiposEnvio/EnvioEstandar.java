package src.models.tiposEnvio;
import src.models.Envio;
import src.models.Paquete;
import java.util.List;

public class EnvioEstandar extends Envio {
    //==================== Constructor =====================
    public EnvioEstandar(List<Paquete> paquetes) {
        super(paquetes);
    }

    //==================== Obtener el costo del envío estándar =====================
    @Override
    public double getCosto() {
        // Costo base para envío estándar
        double costoBase = super.getCosto();
        // No hay recargos adicionales para envío estándar
        return costoBase;
    }

    //==================== 2.1. MODIFICADO: Obtener resumen del envío estándar (NUEVO) =====================
    @Override
    public String generarResumen() {
        double costoBase = super.getCosto();
        double recargoPeso = super.getRecargo();
        double costoTotal = getCosto() + recargoPeso; // costo estándar + recargo por peso

        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Envío Estándar:\n");
        resumen.append("\t Envío con paquetes: ").append(getPaquetes().size()).append("\n");
        resumen.append("\t Costo base: $").append(costoBase).append("\n");
        resumen.append("\t Recargo por peso excesivo: $").append(recargoPeso).append("\n");
        resumen.append("\t Costo total: $").append(costoTotal).append("\n");
        resumen.append("Detalles de los paquetes:\n");

        for (Paquete p : getPaquetes()) {
            resumen.append("\t").append(p.toString()).append("\n");
        }

        return resumen.toString();
    }
}