package daw;

import java.util.Arrays;
import java.util.Random;

public class Tablero {

    private Celula[][] tablero;

    public Tablero (Tablero tablero) {
        
    }

    // Genera un tablero de tamaño NxN con celulas, todas muertas en inicio
    public Tablero (int tamaño) {
        tablero = new Celula[tamaño][tamaño];   
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = new Celula(new Posicion(i, j, tamaño), false, tamaño);
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }

    // Aqui esta lo guapo
    // Hay que conseguir que se actualice el tablero
    // teniendo en cuenta que hay que actualizarlo todo a la vez
    public void actualizarTablero() {
        // Ambos bucles hacen la mismo
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j].actualizarSiguienteEstado(tablero);
            }
        }
        for (Celula[] filas : tablero) {
            for (Celula celula : filas) {
                celula.actualizarCelula();
            }
        }     
    }

    public void colocarCelulasAleatorias(int porcentaje) {  
        Random r = new Random();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Celula(new Posicion(i, j, tablero.length), r.nextInt(1, 101) <= porcentaje, tablero.length);
            }
        }
    }

    // Aqui tenemos que llegar con el tablero inicializado
    public void colocarCelulasManualmente(String posStr) {
        Posicion pos = new Posicion();
        try {
            pos = Posicion.parsePosicion(posStr);
        } catch (IllegalArgumentException iae) {
            System.out.println("Posicion no valida");
        }

        if (pos.getFila() != -1) {
            // Es valida
            tablero[pos.getFila()][pos.getColumna()].setEstado(true);
        }
        
    }
    


    public Celula[][] getTablero() {
        return tablero;
    }

    public void setTablero(Celula[][] tablero) {
        this.tablero = tablero;
    }

    public void setEstadoCelula(Posicion posicion, boolean estado) {
        tablero[posicion.getFila()][posicion.getColumna()].setEstado(estado);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(tablero);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tablero other = (Tablero) obj;
        if (!Arrays.deepEquals(tablero, other.tablero))
            return false;
        return true;
    }
   
}
