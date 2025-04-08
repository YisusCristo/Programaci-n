public class Gato extends Animal {
    private boolean testLeucemia;
    
    public Gato(String chip, String nombre, int edad, String raza, boolean adoptado, boolean testLeucemia) {
        super(chip, nombre, edad, raza, adoptado);
        this.testLeucemia = testLeucemia;
    }
    
    @Override
    public void mostrar() {
        System.out.println("Gato:\nChip: " + chip + "\nNombre: " + nombre + 
                          "\nEdad: " + edad + "\nRaza: " + raza + 
                          "\nAdoptado: " + (adoptado ? "Sí" : "No") + 
                          "\nTest Leucemia: " + (testLeucemia ? "Positivo" : "Negativo"));
    }
}

