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
    public double getCosto() {
        // Costo base para envío internacional
        double costoBase = super.getCosto();
        // Recargo adicional para envío internacional
        return costoBase * 2.0; // 100% más caro que el estándar
    }
    //==================== Mostrar resumen del envío internacional =====================
    @Override
    public void mostrarResumen() {
        System.out.println("Resumen del Envío Internacional:");
        System.out.println("\t Envío con paquetes: " + getPaquetes().size());
        
        double costoBase = super.getCosto();
        double recargoPeso = super.getRecargo();
        double recargoInternacional = costoBase * 0.5; // el 50% extra
        double costoTotal = getCosto() + recargoPeso; // costo internacional + recargo por peso

        System.out.println("\t Costo base: $" + costoBase);
        System.out.println("\t Recargo por peso excesivo: $" + recargoPeso);
        System.out.println("\t Recargo por envío Internacional: $" + recargoInternacional);
        System.out.println("\t Costo total: $" + costoTotal);

        System.out.println("Detalles de los paquetes:");
        for (Paquete p : getPaquetes()) {
            p.mostrarInformacion();
        }
    }
}