import java.util.HashMap;
public class ComprobadorChip {
    private HashMap<String, Animal> animales;
    
    public ComprobadorChip() {
        animales = new HashMap<>();
    }
    
    public boolean darAltaAnimal(Animal animal) {
        if (animales.containsKey(animal.getChip())) {
            System.out.println("Error: Ya existe un animal con ese chip :-)");
            return false;
        }
        animales.put(animal.getChip(), animal);
        System.out.println("!Animal dado de alta!");
        return true;
    }
    
    public void buscarAnimal(String chip) {
        Animal animal = animales.get(chip);
        if (animal != null) {
            animal.mostrar();
        } else {
            System.out.println("No se encontró un animal con ese chip prueba de nuevo");
        }
    }
}

