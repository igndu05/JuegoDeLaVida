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

    
}
