package joel.adat;

import com.db4o.ObjectContainer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import joel.adat.BBDD.ConexionDB;
import joel.adat.DAO.*;
import joel.adat.MODEL.Distrito;
import joel.adat.MODEL.Habitante;
import joel.adat.MODEL.Juego;
import joel.adat.MODEL.Tributo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File databaseFile = new File("juegos_del_hambreJoel.db4o");
        if (!databaseFile.exists()) {
            try {
                if (databaseFile.createNewFile()) {
                    System.out.println("Archivo 'juegos_del_hambreJoel.db4o' generado exitosamente.");
                } else {
                    System.out.println("No se pudo generar el archivo 'juegos_del_hambreJoel.db4o'.");
                }
            } catch (IOException e) {
                System.out.println("Error al generar el archivo 'juegos_del_hambreJoel.db4o': " + e.getMessage());
            }
        } else {
            System.out.println("El archivo 'juegos_del_hambreJoel.db4o' ya existe. No se generará un nuevo archivo.");
        }

        ObjectContainer bbdd = new ConexionDB().getConnection();
        Scanner scanner = new Scanner(System.in);
        Distrito d = new Distrito();
        Habitante h = new Habitante();
        Juego j = new Juego();
        File ficheroDistritos;
        File ficheroHabitantes;
        File ficheroJuegos;
        try {
            ficheroDistritos = new File(d.getClass().getResource("/csv/distritos.csv").toURI());
            ficheroHabitantes = new File(h.getClass().getResource("/csv/habitantes.csv").toURI());
            ficheroJuegos = new File(j.getClass().getResource("/csv/juegos.csv").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        // cargar
        cargarDatosDistrito(ficheroDistritos, bbdd);
        cargarDatosHabitantes(ficheroHabitantes, bbdd);
        cargarDatosJuegos(ficheroJuegos, bbdd);


    }

    public static void cargarDatosDistrito(File ruta, ObjectContainer db) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            List<String[]> lineas = reader.readAll();
            List<String[]> lineasSinEncabezado = new ArrayList<>(lineas.subList(1, lineas.size()));

            for (String[] linea : lineasSinEncabezado) {
                Distrito dis = DaoDistrito.getByName(linea[1], db);
                if (dis == null) {
                    Habitante h = new Habitante();
                    dis = new Distrito(Integer.parseInt(linea[0]), linea[1], null);

                    List<Habitante> listaDehabitantes = new ArrayList<>();
                    try (CSVReader leerhabitantes = new CSVReader(new FileReader(new File(h.getClass().getResource("/csv/habitantes.csv").toURI())))) {
                        List<String[]> lhab = leerhabitantes.readAll();
                        List<String[]> lhabSinEncabezado = new ArrayList<>(lhab.subList(1, lhab.size()));

                        for (String[] lineaHab : lhabSinEncabezado) {
                            if (Integer.parseInt(lineaHab[4]) == dis.getIdDistrito()) {
                                listaDehabitantes.add(new Habitante(
                                        Integer.parseInt(lineaHab[0]),
                                        Integer.parseInt(lineaHab[2]),
                                        lineaHab[1],
                                        Boolean.parseBoolean(lineaHab[3])
                                ));
                            }
                        }

                        dis.setHabitantes(listaDehabitantes);
                    } catch (CsvException | IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
                DaoDistrito.insert(dis, db);
            }
        } catch (FileNotFoundException | CsvException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void cargarDatosHabitantes(File ruta, ObjectContainer db) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            List<String[]> lineas = reader.readAll();
            lineas.removeFirst();

            //x cada linea evaluamos el objeto que se va a crear
            for (String[] linea : lineas) {
                Habitante hab = DaoHabitante.getByName(linea[1],db);
                if (hab==null){
                    //crea habitante
                    hab = new Habitante(Integer.parseInt(linea[0]),Integer.parseInt(linea[2]),linea[1],Boolean.parseBoolean(linea[3]));
                }
                DaoHabitante.insert(hab, db);
            }
        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarDatosJuegos(File ruta, ObjectContainer db) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            List<String[]> lineas = reader.readAll();
            List<String[]> lineasSinEncabezado = new ArrayList<>(lineas.subList(1, lineas.size()));

            for (String[] linea : lineasSinEncabezado) {
                Juego jue = DaoJuego.getByYear(Integer.parseInt(linea[1]), db);
                if (jue == null) {
                    Habitante h = new Habitante();
                    jue = new Juego(Integer.parseInt(linea[0]), null, null);

                    List<Tributo> listaDetributos = new ArrayList<>();
                    try (CSVReader leertributo = new CSVReader(new FileReader(new File(h.getClass().getResource("/csv/habitantes.csv").toURI())))) {
                        List<String[]> ltrib = leertributo.readAll();
                        List<String[]> ltribSinEncabezado = new ArrayList<>(ltrib.subList(1, ltrib.size()));

                        for (String[] lineatrib : ltribSinEncabezado) {
                            if (Integer.parseInt(lineatrib[0]) == h.getIdHabitante()) {
                                Habitante habit = new Habitante(
                                        Integer.parseInt(lineatrib[0]),
                                        Integer.parseInt(lineatrib[2]),
                                        lineatrib[1],
                                        Boolean.parseBoolean(lineatrib[3])
                                );
                                listaDetributos.add(new Tributo(habit, Integer.parseInt(lineatrib[6]), lineatrib[5]));
                            }
                        }

                        jue.setTributos(listaDetributos);
                    } catch (CsvException | IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        int idGanador = Integer.parseInt(linea[3]);
                        Tributo ganador = DaoTributo.getById(idGanador, db);
                        if (ganador != null) {
                            jue.setGanador(ganador);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error al cargar el ganador: " + e.getMessage());
                    }
                }
                DaoJuego.insert(jue, db);
            }
        } catch (FileNotFoundException | CsvException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}