'''
CUESTIÓN 3: Simular el funcionamiento de una cuenta bancaria
Al iniciar el programa, pediremos el saldo inicial de la cuenta (puede ser un número decimal), si el saldo es menor que 0 se volverá a pedir hasta que sea correcto. 
Posteriormente mostraremos un menú con las opciones, 1-ingresar dinero, 2-retirar dinero y 3- mostrar saldo y 4-salir, si la opción no es correcta se volver a pedir de nuevo hasta que sea correcta. No se pueden ingresar cantidades negativas y no podemos retirar dinero si nos quedamos en números rojos.  Máxima puntuación (3 puntos): incluir una opción más en el menú, estadísticas, que nos muestre cuántos ingresos y retiradas se han efectuado.
'''


def obtener_saldo_inicial():
    while True:
        try:
            saldo = float(input("Introduce el saldo inicial de la cuenta: "))
            if saldo >= 0:
                return saldo
            else:
                print("El saldo no puede ser negativo. Intenta de nuevo.")
        except ValueError:
            print("Entrada no válida. Por favor, introduce un número.")

def mostrar_menu():
    print("\nMenú:")
    print("1 - Ingresar dinero")
    print("2 - Retirar dinero")
    print("3 - Mostrar saldo")
    print("4 - Estadísticas")
    print("5 - Salir")

def main():
    saldo = obtener_saldo_inicial()
    ingresos = 0
    retiros = 0

    while True:
        mostrar_menu()
        opcion = input("Selecciona una opción (1-5): ")

        if opcion == '1':
            try:
                cantidad = float(input("Introduce la cantidad a ingresar: "))
                if cantidad < 0:
                    print("No se pueden ingresar cantidades negativas.")
                else:
                    saldo += cantidad
                    ingresos += 1
                    print(f"Has ingresado {cantidad}. Nuevo saldo: {saldo}")
            except ValueError:
                print("Entrada no válida. Por favor, introduce un número.")

        elif opcion == '2':
            try:
                cantidad = float(input("Introduce la cantidad a retirar: "))
                if cantidad < 0:
                    print("No se pueden retirar cantidades negativas.")
                elif cantidad > saldo:
                    print("No puedes retirar más dinero del que tienes.")
                else:
                    saldo -= cantidad
                    retiros += 1
                    print(f"Has retirado {cantidad}. Nuevo saldo: {saldo}")
            except ValueError:
                print("Entrada no válida. Por favor, introduce un número.")

        elif opcion == '3':
            print(f"Tu saldo actual es: {saldo}")

        elif opcion == '4':
            print(f"Estadísticas:")
            print(f"Ingresos efectuados: {ingresos}")
            print(f"Retiros efectuados: {retiros}")

        elif opcion == '5':
            print("Saliendo del programa.")
            break

        else:
            print("Opción incorrecta. Por favor, intenta de nuevo.")

if __name__ == "__main__":
    main()
