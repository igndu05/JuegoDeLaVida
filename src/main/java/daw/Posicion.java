package daw;
import java.util.HashSet;


public class Posicion {
    private int columna;
    private int fila;

    public Posicion(int columna, int fila) {
        if (columna < 0 || fila < 0) {
            throw new IllegalArgumentException("La columna y la fila deben ser mayores o iguales a 0");
        }

        this.columna = columna;
        this.fila = fila;          
    }

    public Posicion() {
        this.columna = -1;
        this.fila = -1;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + columna;
        result = prime * result + fila;
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
        Posicion other = (Posicion) obj;
        if (columna != other.columna)
            return false;
        if (fila != other.fila)
            return false;
        return true;
    }

    public boolean isValida(int tamaño) {
        return columna >= 0 && columna <= tamaño && fila >= 0 && fila <= tamaño;
    }

    public HashSet<Posicion> recorrerAbyacentes(int tamaño) {
        HashSet<Posicion> adyacentes = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                try {
                    adyacentes.add(new Posicion(columna + i, fila + j));
                    // Como en el constructor de Posicion se lanza una excepcion 
                    // si la posicion no es valida, no es necesario comprobar si es valida por el try catch
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }
        return adyacentes;
    }


}
