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
    public double getCosto() {
        // Costo base para envío estándar
        double costoBase = super.getCosto();
        // No hay recargos adicionales para envío estándar
        return costoBase;
    }
}