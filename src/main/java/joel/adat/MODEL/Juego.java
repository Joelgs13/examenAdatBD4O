package joel.adat.MODEL;

import java.util.List;

public class Juego {
    int anio;
    List<Tributo> tributos;
    Tributo ganador;

    public Juego(){

    }

    public Juego(int anio, List<Tributo> tributos, Tributo ganador) {
        this.anio = anio;
        this.tributos = tributos;
        this.ganador = ganador;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<Tributo> getTributos() {
        return tributos;
    }

    public void setTributos(List<Tributo> tributos) {
        this.tributos = tributos;
    }

    public Tributo getGanador() {
        return ganador;
    }

    public void setGanador(Tributo ganador) {
        this.ganador = ganador;
    }
}
