package src.models.tiposEnvio;
import src.models.Envio;
import src.models.Paquete;
import java.util.List;

public class EnvioInternacional extends Envio {
    //==================== Constructor =====================
    public EnvioInternacional(List<Paquete> paquetes) {
        super(paquetes);
    } 

    //==================== Obtener el costo del envío internacional =====================
    @Override
    public double getCosto() {
        // Costo base para envío internacional
        double costoBase = super.getCosto();
        // Recargo adicional para envío internacional
        return costoBase * 2.0; // 100% más caro que el estándar
    }

    //==================== Obtener resumen del envío internacional (MODIFICADO) =====================
    @Override
    public String generarResumen() {
        double costoBase = super.getCosto();
        double recargoPeso = super.getRecargo();
        double recargoInternacional = costoBase * 1.0; // el 100% extra (costoBase * 2.0 - costoBase)
        double costoTotal = getCosto() + recargoPeso; // costo internacional + recargo por peso

        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Envío Internacional:\n");
        resumen.append("\t Envío con paquetes: ").append(getPaquetes().size()).append("\n");
        resumen.append("\t Costo base: $").append(costoBase).append("\n");
        resumen.append("\t Recargo por peso excesivo: $").append(recargoPeso).append("\n");
        resumen.append("\t Recargo por envío Internacional: $").append(recargoInternacional).append("\n");
        resumen.append("\t Costo total: $").append(costoTotal).append("\n");
        resumen.append("Detalles de los paquetes:\n");

        for (Paquete p : getPaquetes()) {
            resumen.append("\t").append(p.toString()).append("\n");
        }

        return resumen.toString();
    }
}