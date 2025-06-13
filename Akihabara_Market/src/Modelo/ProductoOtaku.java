package Modelo;

public class ProductoOtaku {
    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private String descripcion;
    private int stock;

    // Constructor SIN ID (para nuevos productos que se van a insertar)
    public ProductoOtaku(String nombre, String categoria, double precio, String descripcion, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Constructor CON ID (para productos existentes cargados desde la BD)
    public ProductoOtaku(int id, String nombre, String categoria, double precio, String descripcion, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
