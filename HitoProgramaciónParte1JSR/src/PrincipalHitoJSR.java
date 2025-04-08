import java.util.Scanner;
public class PrincipalHitoJSR {
    public static void main(String[] args) {
        ComprobadorChip sistema = new ComprobadorChip();

        iniciarAnimales(sistema);
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1.-Dar de alta Perro");
            System.out.println("2.-Dar de alta Gato");
            System.out.println("3.-Buscar Animal por el número de chip");
            System.out.println("4.-Salir de la apliacción");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 4) {
                System.out.println("!Gracias por confiar en nosotros!");
                break;
            }

            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción no disponible elige una opción del 1 - 4 .");
                continue;
            }

            if (opcion == 3) {
                System.out.print("Ingresa el número del chip: ");
                sistema.buscarAnimal(scanner.nextLine());
                continue;
            }

            // Datos para perro y gato
            System.out.print("Chip: ");
            String chip = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Raza del animal: ");
            String raza = scanner.nextLine();
            System.out.print("¿Adoptado? (true (si) /false (no): ");
            boolean adoptado = scanner.nextBoolean();

            if (opcion == 1) {
                scanner.nextLine();
                System.out.print("¿Como de grande es el Perro? (pequeño/mediano/grande): ");
                sistema.darAltaAnimal(new Perro(chip, nombre, edad, raza, adoptado, scanner.nextLine()));
            } else {
                System.out.print("¿Tiene Leucemia? (true (si) /false (no): ");
                sistema.darAltaAnimal(new Gato(chip, nombre, edad, raza, adoptado, scanner.nextBoolean()));
            }
        }
        scanner.close();
    }

    private static void iniciarAnimales(ComprobadorChip sistema) {
        sistema.darAltaAnimal(new Perro("001", "Tobi", 4, "Pastor Alemán", false, "grande"));
        sistema.darAltaAnimal(new Perro("002", "Richi", 2, "Bulldog Francés", true, "pequeño"));
        sistema.darAltaAnimal(new Gato("003", "Garfield", 3, "Persa", false, true));
        sistema.darAltaAnimal(new Gato("004", "Amanda", 1, "Siamés", true, false));
    }
}