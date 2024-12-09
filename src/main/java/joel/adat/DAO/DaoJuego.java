package joel.adat.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import joel.adat.MODEL.Juego;

public class DaoJuego {
    public static void insert(Juego j, ObjectContainer db) {
        db.store(j);
    }

    public static Juego getByYear(int anio, ObjectContainer bd){
        Juego t = new Juego();
        t.setAnio(anio);
        ObjectSet<Juego> set = bd.queryByExample(t);
        return set.hasNext() ? set.next() : null;
    }
}
