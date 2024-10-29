'''
CUESTIÓN 1: Mostrar figuras por pantalla (2,5 puntos): a través de un menú solicitaremos al
usuario que tipo de figura quiere mostrar (1-Cuadrado|2-Rectángulo), si la opción no es
correcta, se mostrará mensaje de error y se volverá a solicitar hasta que se correcta.
'''

class Figura:
    def area(self):
        pass

    def perimetro(self):
        pass

    def mostrar(self):
        pass


class Cuadrado(Figura):
    def __init__(self, lado):
        self.lado = lado

    def area(self):
        return self.lado ** 2

    def perimetro(self):
        return self.lado * 4

    def mostrar(self):
        for _ in range(int(self.lado)):
            print('* ' * int(self.lado))


class Rectangulo(Figura):
    def __init__(self, base, altura):
        self.base = base
        self.altura = altura

    def area(self):
        return self.base * self.altura

    def perimetro(self):
        return 2 * (self.base + self.altura)

    def mostrar(self):
        for _ in range(int(self.altura)):
            print('* ' * int(self.base))


def obtener_numero(mensaje):
    while True:
        try:
            numero = float(input(mensaje))
            if numero <= 0:
                print("Por favor, introduce un número positivo.")
                continue
            return numero
        except ValueError:
            print("Entrada no válida. Por favor, introduce un número.")


def main():
    while True:
        print("Selecciona la figura a mostrar:")
        print("1 - Cuadrado")
        print("2 - Rectángulo")

        opcion = input("Introduce tu opción (1 o 2): ")

        if opcion == '1':
            lado = obtener_numero("Introduce el lado del cuadrado: ")
            cuadrado = Cuadrado(lado)
            cuadrado.mostrar()
            print(f"Área del cuadrado: {cuadrado.area()}")
            print(f"Perímetro del cuadrado: {cuadrado.perimetro()}")
            break
        elif opcion == '2':
            base = obtener_numero("Introduce la base del rectángulo: ")
            altura = obtener_numero("Introduce la altura del rectángulo: ")
            rectangulo = Rectangulo(base, altura)
            rectangulo.mostrar()
            print(f"Área del rectángulo: {rectangulo.area()}")
            print(f"Perímetro del rectángulo: {rectangulo.perimetro()}")
            break
        else:
            print("Opción incorrecta. Por favor, intenta de nuevo.")


if __name__ == "__main__":
    main()
