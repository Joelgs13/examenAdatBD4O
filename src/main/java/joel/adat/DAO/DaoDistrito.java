package joel.adat.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import joel.adat.MODEL.Distrito;

public class DaoDistrito {
    public static void insert(Distrito d, ObjectContainer db) {
        db.store(d);
    }

    public static Distrito getByName(String nombre, ObjectContainer bd){
        Distrito d = new Distrito();
        d.setNombreDistrito(nombre);
        ObjectSet<Distrito> set = bd.queryByExample(d);
        return set.hasNext() ? set.next() : null;
    }
}
