package daw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        celulasGeneracion.add(contarCelulasVivas(ultimoTablero));
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
    
        String idFichero = "generacion" + (celulasGeneracion.size() + 1) + ".txt";
        String tmp;
        Celula[][] tableroActual = tablero.getTablero();
        
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            flujo.write(tablero.getTablero().length + "\n");
            flujo.write(contarCelulasVivas(tablero) + "\n");


            for (int i = 0; i < tableroActual.length; i++) {
                for (int j = 0; j < tableroActual[i].length; j++) {
                    flujo.write(tableroActual[i][j].toStringB());
                }
                // Metodo newLine() añade salto de línea después de cada fila
                flujo.newLine();
            }
            
            for (Integer celulas : celulasGeneracion) {
                flujo.write(celulas);
                flujo.write(" ");
            }

            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void leerFichero () {
            
    }
    
}
