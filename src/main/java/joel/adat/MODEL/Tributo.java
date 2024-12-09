package joel.adat.MODEL;

public class Tributo extends Habitante{
    int puntaje;
    String habilidad;

    public Tributo() {
    }

    public Tributo(int puntaje, String habilidad) {
        super();
        this.puntaje = puntaje;
        this.habilidad = habilidad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }
}
