package joel.adat.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import joel.adat.MODEL.Habitante;


public class DaoHabitante {
    public static void insert(Habitante h, ObjectContainer db) {
        db.store(h);
    }

    public static Habitante getByName(String nombre, ObjectContainer bd){
        Habitante h = new Habitante();
        h.setNombreHabitante(nombre);
        ObjectSet<Habitante> set = bd.queryByExample(h);
        return set.hasNext() ? set.next() : null;
    }
}
