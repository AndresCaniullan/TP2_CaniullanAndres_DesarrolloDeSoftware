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
    //==================== Mostrar resumen del envío expres =====================
    @Override
    public void mostrarResumen() {
        System.out.println("Resumen del Envío Exprés:");
        System.out.println("\t Envío con paquetes: " + getPaquetes().size());
        
        double costoBase = super.getCosto();
        double recargoPeso = super.getRecargo();
        double recargoExpres = costoBase * 0.5; // el 50% extra
        double costoTotal = getCosto() + recargoPeso; // costo expres + recargo por peso

        System.out.println("\t Costo base: $" + costoBase);
        System.out.println("\t Recargo por peso excesivo: $" + recargoPeso);
        System.out.println("\t Recargo por envío exprés: $" + recargoExpres);
        System.out.println("\t Costo total: $" + costoTotal);

        System.out.println("Detalles de los paquetes:");
        for (Paquete p : getPaquetes()) {
            p.mostrarInformacion();
        }
    }
}