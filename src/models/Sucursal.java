package src.models;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Sucursal {
    // ===================== Atributos =====================
    private int idSucursal;
    private String ciudad;
    private String direccion;
    private List<Envio> enviosEnProceso;
    // ===================== Constructor =====================
    public Sucursal(int idSucursal, String ciudad, String direccion) {
        this.idSucursal = idSucursal;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.enviosEnProceso = new ArrayList<>();
    }
    // ===================== Getters =====================
    public int getIdSucursal() {
        return idSucursal;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getDireccion() {
        return direccion;
    }
    public List<Envio> getEnviosEnProceso() {
        return Collections.unmodifiableList(enviosEnProceso);
    }
    // ===================== Gestionar envíos =====================
    // Método para recibir un envío en la sucursal, agregándolo a la lista de envíos en proceso
    public void recibirEnvio(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo.");
        }
        if (enviosEnProceso.contains(envio)) {
            throw new IllegalStateException("El envío ya se encuentra en proceso en esta sucursal.");
        }
        envio.recibir(this);
        this.enviosEnProceso.add(envio);
    }
    // Método para despachar un envío desde la sucursal, eliminándolo de la lista de envíos en proceso
    public void despacharEnvio(Envio envio) {
        if (!enviosEnProceso.contains(envio)) {
            throw new IllegalStateException("El envío no se encuentra en proceso en esta sucursal.");
        }
        envio.despachar(this);
        this.enviosEnProceso.remove(envio);
    }
    // Método para entregar un envío desde la sucursal, eliminándolo de la lista de envíos en proceso
    public void entregarEnvio(Envio envio) {
    if (envio == null) {
        throw new IllegalArgumentException("El envío no puede ser nulo.");
    }
    envio.entregar(this);
    this.enviosEnProceso.remove(envio);
    }
}