package joel.adat.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import joel.adat.MODEL.Habitante;
import joel.adat.MODEL.Tributo;

public class DaoTributo {

    public static void insert(Tributo t, ObjectContainer db) {
        db.store(t);
    }

    public static Tributo getByName(String nombre, ObjectContainer bd){
        Tributo t = new Tributo();
        t.setNombreHabitante(nombre);
        ObjectSet<Tributo> set = bd.queryByExample(t);
        return set.hasNext() ? set.next() : null;
    }

    public static Tributo getById(int id, ObjectContainer bd) {
        Habitante h = new Habitante();
        h.setIdHabitante(id);

        ObjectSet<Habitante> setHabitante = bd.queryByExample(h);
        if (!setHabitante.hasNext()) {
            return null; // Si no se encuentra el habitante, devolver null
        }

        Habitante habitante = setHabitante.next();

        if (!habitante.isEsTributo()) {
            return null; // Si no es tributo, devolver null
        }

        Tributo tributo = new Tributo();
        tributo.setIdHabitante(habitante.getIdHabitante());
        tributo.setEdad(habitante.getEdad());
        tributo.setNombreHabitante(habitante.getNombreHabitante());
        tributo.setEsTributo(true);

        tributo.setHabilidad("Habilidad predeterminada");
        tributo.setPuntaje(0);

        return tributo;
    }
}
