package joel.adat.MODEL;

public class Habitante {
    int idHabitante, edad;
    String nombreHabitante;
    boolean esTributo;

    public Habitante(){
    }

    public Habitante(int idHabitante, int edad, String nombreHabitante, boolean esTributo) {
        this.idHabitante = idHabitante;
        this.edad = edad;
        this.nombreHabitante = nombreHabitante;
        this.esTributo = esTributo;
    }

    public int getIdHabitante() {
        return idHabitante;
    }

    public void setIdHabitante(int idHabitante) {
        this.idHabitante = idHabitante;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreHabitante() {
        return nombreHabitante;
    }

    public void setNombreHabitante(String nombreHabitante) {
        this.nombreHabitante = nombreHabitante;
    }

    public boolean isEsTributo() {
        return esTributo;
    }

    public void setEsTributo(boolean esTributo) {
        this.esTributo = esTributo;
    }
}
