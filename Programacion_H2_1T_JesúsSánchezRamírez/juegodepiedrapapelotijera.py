'''
CUESTIÓN 2: Juego de piedra papel o tijera (2,5 puntos). El usuario introduce un valor (1-
Piedra|2- Papel|3-Tijera), si no es correcto se volver a pedir de nuevo hasta que se correcta.
La “maquina” generará un valor aleatorio (de 1 a 3) para elegir piedra, papel o tijera. Al
finalizar, mostrará la opción del usuario y de la máquina e indicará si hemos ganado, perdido o
empatado.
'''

import random

def obtener_opcion_usuario():
    while True:
        print("Elige una opción:")
        print("1 - Piedra")
        print("2 - Papel")
        print("3 - Tijera")
        opcion = input("Introduce tu opción (1, 2 o 3): ")
        
        if opcion in ['1', '2', '3']:
            return int(opcion)
        else:
            print("Opción incorrecta. Por favor, intenta de nuevo.")

def obtener_opcion_maquina():
    return random.randint(1, 3)

def determinar_ganador(usuario, maquina):
    if usuario == maquina:
        return "Empate"
    elif (usuario == 1 and maquina == 3) or (usuario == 2 and maquina == 1) or (usuario == 3 and maquina == 2):
        return "Ganaste"
    else:
        return "Perdiste"

def main():
    puntuacion_usuario = 0
    puntuacion_maquina = 0

    while puntuacion_usuario < 3 and puntuacion_maquina < 3:
        opcion_usuario = obtener_opcion_usuario()
        opcion_maquina = obtener_opcion_maquina()
        
        print(f"\nTu opción: {opcion_usuario} - {'Piedra' if opcion_usuario == 1 else 'Papel' if opcion_usuario == 2 else 'Tijera'}")
        print(f"Opción de la máquina: {opcion_maquina} - {'Piedra' if opcion_maquina == 1 else 'Papel' if opcion_maquina == 2 else 'Tijera'}")
        
        resultado = determinar_ganador(opcion_usuario, opcion_maquina)
        print(f"Resultado: {resultado}\n")

        if resultado == "Ganaste":
            puntuacion_usuario += 1
        elif resultado == "Perdiste":
            puntuacion_maquina += 1

    if puntuacion_usuario == 3:
        print("¡Felicidades! Has ganado el juego.")
    else:
        print("Lo siento, la máquina ha ganado el juego.")

if __name__ == "__main__":
    main()
