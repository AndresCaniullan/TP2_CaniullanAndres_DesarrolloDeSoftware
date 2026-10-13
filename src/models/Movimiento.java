package src.models;
import src.enums.TipoMovimiento;
import java.time.LocalDateTime;

public class Movimiento {
    //================ Atributos ==========================
    private Sucursal sucursal;
    private TipoMovimiento tipoMovimiento;
    private LocalDateTime fechaHora;
    //================ Constructor ==========================
    public Movimiento(Sucursal sucursal, TipoMovimiento tipoMovimiento, LocalDateTime fechaHora) {
        this.sucursal = sucursal;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaHora = fechaHora;
    }
    //================ Getters ==========================
    public Sucursal getSucursal() {
        return sucursal;
    }
    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    //================  Mostrar la información del movimiento de manera legible ==========================
    @Override
    public String toString(){
        return fechaHora + " - " + tipoMovimiento + " en sucursal: N°" + sucursal.getIdSucursal() + " - " + sucursal.getCiudad() + ", " + sucursal.getDireccion();
    }
}
