public class Perro extends Animal {
    private String tamaño;
    
    public Perro(String chip, String nombre, int edad, String raza, boolean adoptado, String tamaño) {
        super(chip, nombre, edad, raza, adoptado);
        this.tamaño = tamaño;
    }
    
    @Override 
    public void mostrar() {
        System.out.println("Perro:\nChip: " + chip + "\nNombre: " + nombre + 
                          "\nEdad: " + edad + "\nRaza: " + raza + 
                          "\nAdoptado: " + (adoptado ? "Sí" : "No") + 
                          "\nTamaño: " + tamaño);
    }
}