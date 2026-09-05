package src.models;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Cliente {
    //================ Atributos ==========================
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String cuilCuit;
    private List<Envio> envios;
    //================ Constructor ==========================
    public Cliente(String nombre, String apellido, String direccion, String telefono, String email, String cuilCuit) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuilCuit = cuilCuit;
        this.envios = new ArrayList<>();
    }
    //================ Getters ==========================
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getCuilCuit() {
        return cuilCuit;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public List<Envio> getEnvios() {
        return Collections.unmodifiableList(envios);
    }
    //================ Gestionar envíos ==========================
    // Método para agregar un envío a la lista de envíos del cliente
    public void agregarEnvio(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo.");
        }
        this.envios.add(envio);
    }
    // Método para obtener la cantidad de envíos del cliente
    public int cantidadEnvios() {
        return envios.size();
    }
    // Método para mostrar los envíos del cliente, utilizando el método mostrarResumen() de la clase Envio
    public void mostrarEnvios(){
        for (Envio e : envios) {
            e.mostrarResumen();;
        }
    }
}
