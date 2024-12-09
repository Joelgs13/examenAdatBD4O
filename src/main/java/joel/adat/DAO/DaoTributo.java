package joel.adat.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
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
}
