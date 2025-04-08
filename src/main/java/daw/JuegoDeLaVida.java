package daw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JuegoDeLaVida {

    private Tablero tablero;
    private int contador = 0;
    private Tablero ultimoTablero;
    private List<Integer> celulasGeneracion = new ArrayList<>(100);

    public JuegoDeLaVida(int tamaño) {
        tablero = new Tablero(tamaño);
    }

    public JuegoDeLaVida() {
    }

    public void mostrarTablero() {
        tablero.mostrarTablero();
    }

    public void actualizarTablero() {
        ultimoTablero = new Tablero(tablero);
        celulasGeneracion.add(contarCelulasVivas(tablero));
        tablero.actualizarTablero();
        if (tablero.equals(ultimoTablero)) {
            contador++;
            if (contador == 3) {
                throw new IllegalStateException("El tablero no ha cambiado en 3 iteraciones");
            }
        }
    }

    public static int contarCelulasVivas(Tablero tablero) {
        int contador = 0;
        for (Celula[] fila : tablero.getTablero()) {
            for (Celula celula : fila) {
                if (celula.isEstado()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public void colocarCelulasAleatorias(int porcentaje) {
        tablero.colocarCelulasAleatorias(porcentaje);
    }

    public void colocarCelulasManualmente(String posString) {
        tablero.colocarCelulasManualmente(posString);
    }

    // Crear metodos para leer y escribir ficheros
    public void escribirFichero() {

        String idFichero = "generacion" + (celulasGeneracion.size()) + ".txt";
        Celula[][] tableroActual = tablero.getTablero();

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            flujo.write(tablero.getTablero().length + "\n");
            flujo.write(celulasGeneracion.size() + "\n");

            StringBuilder filaSb = new StringBuilder();
            for (int i = 0; i < tableroActual.length; i++) {
                for (int j = 0; j < tableroActual[i].length; j++) {
                    filaSb.append(tableroActual[i][j].toStringB());
                }
                // Eliminamos el último espacio
                filaSb.delete(filaSb.length() - 1, filaSb.length());
                flujo.write(filaSb.toString());
                flujo.newLine();
                filaSb.delete(0, filaSb.length()); // Limpiamos el StringBuilder
            }

            StringBuilder sb = new StringBuilder();
            for (int numeroCelulas : celulasGeneracion) {
                sb.append(numeroCelulas).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            flujo.write(sb.toString());

            // Metodo flush() guarda cambios en disco
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void leerFichero() {
        Scanner sc = new Scanner(System.in);
        int genNumero;
        File archivo;

        do {
            // Control de excepción en la entrada del usuario
            try {
                System.out.println("Indique el numero de la generacion a leer: ");
                genNumero = sc.nextInt();
                archivo = new File("generacion" + genNumero + ".txt");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                sc.nextLine(); // Limpiar el buffer
                continue;
            }

            // Verificar si el archivo existe
            if (archivo.exists()) {
                break;
            }
            System.out.println("Error: El archivo generacion" + genNumero + " no existe. Intente con otro número.");

        } while (true);
        sc.close();

        System.out.println("Leyendo el fichero: " + archivo.getName());

        try (Scanner datosFichero = new Scanner(archivo, "UTF-8")) {
            int tamañoMatriz = datosFichero.nextInt();

            datosFichero.nextInt();

            Tablero nuevoTablero = new Tablero(tamañoMatriz);

            Celula[][] nuevaMatriz = nuevoTablero.getTablero(); // esto hace un alias molto lindou
            datosFichero.nextLine();
            for (int i = 0; i < tamañoMatriz; i++) {
                for (int j = 0; j < tamañoMatriz; j++) {
                    nuevaMatriz[i][j].setEstado(datosFichero.nextInt() == 1);
                }
            }
            this.tablero = nuevoTablero;
            datosFichero.nextLine();
            String[] celulasGeneracion = datosFichero.nextLine().split(" ");
            for (String celula : celulasGeneracion) {
                this.celulasGeneracion.add(Integer.parseInt(celula));
            }
            datosFichero.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
