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

    public void setColumna(int columna) {
        if (columna < 0) {
            throw new IllegalArgumentException("La columna debe ser mayor o igual a 0");
        }
        this.columna = columna;
    }

    public void setFila(int fila) {
        if (fila < 0) {
            throw new IllegalArgumentException("La fila debe ser mayor o igual a 0");
        }
        this.fila = fila;
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

    public HashSet<Posicion> recorrerAbyascente(int filas, int columnas) {

        return null;
    }


}
