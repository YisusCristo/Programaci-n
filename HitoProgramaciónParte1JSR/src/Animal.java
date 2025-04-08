public abstract class Animal {
    protected String chip;
    protected String nombre;
    protected int edad;
    protected String raza;
    protected boolean adoptado;
    
    public Animal(String chip, String nombre, int edad, String raza, boolean adoptado) {
        this.chip = chip;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.adoptado = adoptado;
    }
    
    public String getChip() {
        return chip;
    }
    
    public abstract void mostrar();
}