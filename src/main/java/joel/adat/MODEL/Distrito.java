package joel.adat.MODEL;

import java.util.List;

public class Distrito {

    int idDistrito;
    String nombreDistrito;
    List<Habitante> habitantes;

    public Distrito() {
    }

    public Distrito(int idDistrito, String nombreDistrito, List<Habitante> habitantes) {
        this.idDistrito = idDistrito;
        this.nombreDistrito = nombreDistrito;
        this.habitantes = habitantes;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public List<Habitante> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Habitante> habitantes) {
        this.habitantes = habitantes;
    }
}
