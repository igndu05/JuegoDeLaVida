package daw;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    private static Scanner sc;
    private static JuegoDeLaVida juego;
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        menuPrincipal();
    }
    
    private static void menuPrincipal() {
        int opcion;
        boolean salir = false;
        
        do {
            System.out.println("\n==== JUEGO DE LA VIDA ====");
            System.out.println("1. Iniciar nuevo juego");
            System.out.println("2. Cargar juego");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = introducirNumero(2);
            
            switch (opcion) {
                case 1 -> menuConfiguracion();
                case 2 -> menuCarga();
                case 0 -> {
                    salir = true;
                    System.out.println("¡Gracias por jugar!");
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (!salir);
    }

    private static void menuCarga() {
        juego = new JuegoDeLaVida();
        juego.leerFichero();
    }
    
    private static void menuConfiguracion() {
        int tamaño;
        
        // Solicitar tamaño del tablero
        do {
            System.out.println("\n==== CONFIGURACIÓN DEL JUEGO ====");
            System.out.print("Ingrese el tamaño del tablero (máximo 25): ");
            tamaño = introducirNumero(25);
            
            if (tamaño <= 0 || tamaño > 25) {
                System.out.println("El tamaño debe estar entre 1 y 25.");
            }
        } while (tamaño <= 0 || tamaño > 25);
        
        // Crear el juego con el tamaño especificado
        juego = new JuegoDeLaVida(tamaño);
        
        // Menú para elegir forma de colocar células
        menuColocacionCelulas(tamaño);
    }
    
    private static void menuColocacionCelulas(int tamaño) {
        int opcion;
        
        System.out.println("\n==== COLOCACIÓN DE CÉLULAS ====");
        System.out.println("1. Colocación aleatoria");
        System.out.println("2. Colocación manual");
        System.out.print("Seleccione una opción: ");
        
        opcion = introducirNumero(2);
        
        switch (opcion) {
            case 1 -> {
                // Solicitar porcentaje para la colocación aleatoria
                int porcentaje;
                do {
                    System.out.print("Ingrese el porcentaje de células vivas (1-100): ");
                    porcentaje = introducirNumero(100);
                    
                    if (porcentaje < 1 || porcentaje > 100) {
                        System.out.println("El porcentaje debe estar entre 1 y 100.");
                    }
                } while (porcentaje < 1 || porcentaje > 100);
                
                // Aquí iría la lógica para la colocación aleatoria usando el porcentaje
                System.out.println("Colocación aleatoria seleccionada con " + porcentaje + "% de células vivas");
                juego.colocarCelulasAleatorias(porcentaje);
            }
            case 2 -> {
                // Aquí iría la lógica para la colocación manual
                System.out.println("Colocación manual seleccionada, # para terminar");
                do {
                    System.out.print("Ingrese la posición de la célula (fila columna): ");
                    String posStr = sc.nextLine();
                    if (posStr.equals("#")) {
                        break;
                    }
                    juego.colocarCelulasManualmente(posStr);
                } while (true);
            }
            default -> {
                System.out.println("Opción no válida. Se usará colocación aleatoria.");
                // Lógica por defecto para colocación aleatoria
            }
        }
        
        // Una vez configurado, ir al menú de simulación
        menuSimulacion();
    }
    // ESTO ESTA IMCOMPLETO
    private static void menuSimulacion() {
        int opcion;
        boolean volver = false;
        
        // Mostrar el estado inicial
        System.out.println("\n==== ESTADO INICIAL DEL TABLERO ====");
        juego.mostrarTablero();
        
        do {
            System.out.println("\n==== SIMULACIÓN ====");
            System.out.println("1. Avanzar una generación");
            System.out.println("2. Avanzar múltiples generaciones");
            System.out.println("3. Guardar y salir");
            System.out.println("4. Salir sin guardar");
            System.out.print("Seleccione una opción: ");
            
            opcion = introducirNumero(4);
            
            switch (opcion) {
                case 1 -> {
                    try {
                        juego.actualizarTablero();
                        System.out.println("\n==== NUEVA GENERACIÓN ====");
                        juego.mostrarTablero();
                    } catch (IllegalStateException ise) {
                        System.out.println("El tablero no ha cambiado en 3 iteraciones, finalizando...");
                        volver = true;
                    }
                }
                case 2 -> {
                    System.out.print("Número de generaciones a avanzar: ");
                    int numGeneraciones = introducirNumero(100);
                    boolean finalizado = false;
                    for (int i = 0; i < numGeneraciones; i++) {
                        
                        System.out.println("\n==== GENERACIÓN " + i + " ====");
                        try {
                            juego.actualizarTablero();
                        } catch (IllegalStateException ise) {
                            System.out.println("El tablero no ha cambiado en 3 iteraciones, finalizando...");
                            finalizado = true;
                            volver = true;
                            break;
                        }
                        juego.mostrarTablero();
                        }
                    if (!finalizado) {
                        System.out.println("\n==== GENERACIÓN " + numGeneraciones + " (FINAL) ====");
                        juego.mostrarTablero();
                    }
                }
                case 3 -> {
                    // Aquí iría el código para guardar el estado actual
                    System.out.println("Guardando partida...");
                    juego.escribirFichero();
                    System.out.println("Partida guardada con éxito");
                    volver = true;
                }
                case 4 -> {
                    System.out.println("Saliendo sin guardar...");
                    volver = true;
                }
            }
        } while (!volver);
    }

    private static int introducirNumero(int max) {
        int numero;
        do {
            try {
                numero = sc.nextInt();
                sc.nextLine();
                if (numero <= max) {
                    return numero;
                } else {
                    System.out.println("El valor introducido no es válido. El maximo es " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor introducido no es un número. Intente de nuevo.");
                sc.nextLine();
            }
        } while (true);
    }
} 