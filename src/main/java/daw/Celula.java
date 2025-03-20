package daw;
import java.util.HashSet;

public class Celula {
    private Posicion posicion;
    private boolean estado;
    private final HashSet<Posicion> ADYACENTES;

    public Celula(Posicion posicion, boolean estado, int filas, int columnas) {
        this.posicion = posicion;
        this.estado = estado;
        this.ADYACENTES = this.posicion.recorrerAbyascente(filas, columnas);
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

    @Override
    public String toString() {
        return "Celula [posicion=" + posicion + ", estado=" + estado + "]";
    }

    private int CelulaVivaAlrededor(Celula[][] tableroCelulas) {
        int contador = 0;
        for (Posicion posicion : this.ADYACENTES) {
            if (tableroCelulas[posicion.getFila()][posicion.getColumna()].isEstado() == true) {
                contador += 1;
            }
        }
        return contador;
    }

    public void ActualizarCelula(){
        
    }

    public boolean EstadoCelulaSiguiente(Celula[][] tableroCelulas) {
        int celulasVivas = CelulaVivaAlrededor(tableroCelulas);

        if (this.estado == true) {
            if (celulasVivas > 3) {
                return false;
            } else if (celulasVivas == 2 || celulasVivas == 3) {
                return true;
            } else if (celulasVivas == 0 || celulasVivas == 1) {
                return false;
            }
        } else {
            if (celulasVivas == 3) {
                return true;
            }
        }
        return this.estado;
    }
}
