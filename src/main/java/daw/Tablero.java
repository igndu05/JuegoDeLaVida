package daw;

public class Tablero {

    private Celula[][] tablero;

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
    }

    public void colocarCelulasAleatorias(int porcentaje) {  

    }
    public void colocarCelulasManualmente() {

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


    
}
