package daw;


public class JuegoDeLaVida {

    private Tablero tablero;
    private int contador = 0;
    private Tablero ultimoTablero;
    
    public JuegoDeLaVida(int tamaño) {
        tablero = new Tablero(tamaño);
    }

    public void mostrarTablero() {
        tablero.mostrarTablero();
    }

    public void actualizarTablero() {
        ultimoTablero = new Tablero(tablero);
        tablero.actualizarTablero();
        if (tablero.equals(ultimoTablero)) {
            contador++;
            if (contador == 3) {
                // throw;
            }
        }
    }

    public void colocarCelulasAleatorias(int porcentaje) {
        tablero.colocarCelulasAleatorias(porcentaje);
    }
    
    public void colocarCelulasManualmente(String posString) {
        tablero.colocarCelulasManualmente(posString);
    }
    
}
