package daw;
import java.util.HashSet;

public class Celula {

    private Posicion posicion;
    private boolean estado;
    private final HashSet<Posicion> ADYACENTES;
    private boolean siguienteEstado;
    // Necesitamos un atributo mas para no tener problemas con el tablero

    public Celula(Posicion posicion, boolean estado, int filas) {
        this.posicion = posicion;
        this.estado = estado;
        this.ADYACENTES = this.posicion.recorrerAdyacentes(filas);
    }
    
    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
        result = prime * result + (estado ? 1231 : 1237);
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
        Celula other = (Celula) obj;
        if (posicion == null) {
            if (other.posicion != null)
                return false;
        } else if (!posicion.equals(other.posicion))
            return false;
        if (estado != other.estado)
            return false;
        return true;
    }

    // Para ahorrar trabajo en el futuro esto deberia ser blanco o negro segun el estado
    @Override
    public String toString() {
        return (this.estado)?"\u2B1C":"\u2B1B";
    }

    private int celulaVivaAlrededor(Celula[][] tableroCelulas) {
        int contador = 0;
        for (Posicion posicion : this.ADYACENTES) {
            // aqui hay fallos
            if (tableroCelulas[posicion.getFila()][posicion.getColumna()].isEstado() == true) {
                contador += 1;
            }
        }
        return contador;
    }

    public void actualizarSiguienteEstado(Celula[][] tableroCelulas){
        calcularSiguienteEstado(celulaVivaAlrededor(tableroCelulas));    
    }

    public void actualizarCelula(){
        this.estado = siguienteEstado;
    }

    private void calcularSiguienteEstado(int numeroCelulasVivas) {
        siguienteEstado = switch (numeroCelulasVivas) {
            case 0, 1 -> false;
            case 2 -> this.estado;
            case 3 -> true;
            default -> false;
        };          
    }


}
