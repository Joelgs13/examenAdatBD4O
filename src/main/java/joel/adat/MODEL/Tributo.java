package joel.adat.MODEL;

public class Tributo extends Habitante{
    int puntaje;
    String habilidad;
    Habitante habitante;

    public Tributo() {
    }

    public Tributo(Habitante habitante,int puntaje, String habilidad) {
        this.habitante=habitante;
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
