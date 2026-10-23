package src.models.tiposEnvio;
import src.models.Envio;
import src.models.Paquete;
import java.util.List;

public class EnvioExpres extends Envio {
    //==================== Constructor =====================
    public EnvioExpres(List<Paquete> paquetes) {
        super(paquetes);
    }

    //==================== Obtener el costo del envío expres =====================
    @Override
    public double getCosto() {
        // Costo base para envío expres
        double costoBase = super.getCosto();
        // Recargo adicional para envío expres
        return costoBase * 1.5; // 50% más caro que el estándar
    }

    //==================== Obtener resumen del envío expres (MODIFICADO) =====================
    @Override
    public String generarResumen() {
        double costoBase = super.getCosto();
        double recargoPeso = super.getRecargo();
        double recargoExpres = costoBase * 0.5; // el 50% extra
        double costoTotal = getCosto() + recargoPeso; // costo expres + recargo por peso

        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Envío Exprés:\n");
        resumen.append("\t Envío con paquetes: ").append(getPaquetes().size()).append("\n");
        resumen.append("\t Costo base: $").append(costoBase).append("\n");
        resumen.append("\t Recargo por peso excesivo: $").append(recargoPeso).append("\n");
        resumen.append("\t Recargo por envío exprés: $").append(recargoExpres).append("\n");
        resumen.append("\t Costo total: $").append(costoTotal).append("\n");
        resumen.append("Detalles de los paquetes:\n");

        for (Paquete p : getPaquetes()) {
            resumen.append("\t").append(p.toString()).append("\n");
        }

        return resumen.toString();
    }
}