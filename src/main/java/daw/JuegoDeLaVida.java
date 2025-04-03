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
    public void escribirFichero () {
    
        String idFichero = "generacion" + (celulasGeneracion.size()) + ".txt";
        String tmp;
        Celula[][] tableroActual = tablero.getTablero();
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            flujo.write(tablero.getTablero().length + "\n");
            flujo.write(celulasGeneracion.size() + "\n");


            for (int i = 0; i < tableroActual.length; i++) {
                for (int j = 0; j < tableroActual[i].length; j++) {
                    flujo.write(tableroActual[i][j].toStringB());
                }
                // Metodo newLine() añade salto de línea después de cada fila
                flujo.newLine();
            }
            
            for (int numeroCelulas : celulasGeneracion) {
                flujo.write(String.valueOf(numeroCelulas));
                flujo.write(" ");
            }

            // Metodo flush() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void leerFichero () {
        // String idFichero = "generacion7.txt";

        // // Variables para guardar los datos que se van leyendo
        // String[] tokens;
        // String linea;

        // System.out.println("Leyendo el fichero: " + idFichero);

        // // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // // las operaciones con el archivo
        // try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
        //     // hasNextLine devuelve true mientras haya líneas por leer
        //     while (datosFichero.hasNextLine()) {
        //         // Guarda la línea completa en un String
        //         linea = datosFichero.nextLine();
        //         // Se guarda en el array de String cada elemento de la
        //         // línea en función del carácter separador de campos del fichero CSV
        //         tokens = linea.split(";");
        //         for (String string : tokens) {
        //             System.out.print(string + "\t");
        //         }
        //         System.out.println();
        //     }
        // } catch (FileNotFoundException e) {
        //     System.out.println(e.getMessage());
        // }
        String idFichero = "generacion7.txt";

        String[] tokens;
        String linea;

        System.out.println("Leyendo el fichero: " + idFichero);

        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            linea.
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
