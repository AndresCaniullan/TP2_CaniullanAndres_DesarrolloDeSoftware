package src.models;
import src.enums.EstadoPaquete;

public class Paquete {
    // ===================== Atributos =====================
    private int id;
    private String descripcion;
    private double peso;
    private EstadoPaquete estado;
    // ===================== Constructor =====================
    public Paquete(int id, String descripcion, double peso) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.estado = EstadoPaquete.EN_PREPARACION;
    }
    // ===================== Getters =====================
    public int getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public double getPeso() {
        return peso;
    }
    public EstadoPaquete getEstado() {
        return estado;
    }
 // ===================== Consultas de transición =====================
    public boolean puedeEnviar() {
        return this.estado == EstadoPaquete.EN_PREPARACION || this.estado == EstadoPaquete.EN_ALMACEN;
    }
    public boolean puedeAlmacenar() {
        return this.estado == EstadoPaquete.EN_TRANSITO || this.estado == EstadoPaquete.EN_PREPARACION;
    }
    public boolean puedeEntregar() {
        return this.estado == EstadoPaquete.EN_TRANSITO;
    }
    public boolean puedeDevolver() {
        return this.estado == EstadoPaquete.EN_TRANSITO || this.estado == EstadoPaquete.ENTREGADO;
    }
    //===================== Transiciones de estado =====================
    public void preparar() {
        this.estado = EstadoPaquete.EN_PREPARACION;
    }
    public void enviar() {
        if (!puedeEnviar()) {
            throw new IllegalStateException(
                "El paquete " + id + " no puede enviarse desde el estado " + estado);
        }
        this.estado = EstadoPaquete.EN_TRANSITO;
    }
    public void almacenar() {
        if (!puedeAlmacenar()) {
            throw new IllegalStateException(
                "El paquete " + id + " no puede almacenarse desde el estado " + estado);
        }
        this.estado = EstadoPaquete.EN_ALMACEN;
    }
    public void entregar() {
        if (!puedeEntregar()) {
            throw new IllegalStateException(
                "El paquete " + id + " no puede entregarse desde el estado " + estado);
        }
        this.estado = EstadoPaquete.ENTREGADO;
    }
    public void devolver() {
        if (!puedeDevolver()) {
            throw new IllegalStateException(
                "El paquete " + id + " no puede devolverse desde el estado " + estado);
        }
        this.estado = EstadoPaquete.DEVUELTO;
    }
    //===================== Mostrar la información del paquete =====================
    @Override
    public String toString() {
        return "ID: " + id + " | Descripción: " + descripcion
             + " | Peso: " + peso + " kg | Estado: " + estado;
    }
}