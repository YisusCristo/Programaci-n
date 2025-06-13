package Modelo;

import java.util.Date;

public class ClienteOtaku {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;

    public ClienteOtaku(int id, String nombre, String email, String telefono, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public ClienteOtaku(String nombre, String email, String telefono) {
        this(0, nombre, email, telefono, new Date());
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public Date getFechaRegistro() { return fechaRegistro; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
