package mvc;

import db.DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class PeliculaModel {
    private String error = null;

    public String getError() {
        return error;
    }

    public Pelicula convertirDesde(String[] datos) {
        ArrayList<String> datosInvalidos = new ArrayList<>();
        Pelicula e = new Pelicula();


        if(datos.length < 5) {
            error = "No se han proporcionado todos los datos necesarios";
            return null;
        }

        //try {
        //     e.setId_pelicula(Integer.parseInt(datos[0]));
        // }
        //catch (NumberFormatException ex) {
        //    error = "id debe ser num�rico.";
        //    return null;
        //}

        try {
            e.setAnio(Integer.parseInt(datos[3]));
        }
        catch (NumberFormatException ex) {
            error = "Añoo debe ser numérico.";
            return null;
        }

        try {
            e.setDuracion(Integer.parseInt(datos[4]));
        }
        catch (NumberFormatException ex) {
            error = "Duración debe ser numérico.";
            return null;
        }

        e.setTitulo(datos[1]);
        e.setDirector(datos[2]);
        e.setGenero(datos[5]);

        return e;
    }

    public Pelicula[] obtenerPeliculas() {
        error = null;
        Connection conn = null;
        Pelicula[] res = null;

        try {
            conn = DB.getInstance().getConnection();
            ArrayList<Pelicula> lRes = new ArrayList<>();
            CallableStatement cs = conn.prepareCall("{call OBTENER_PELICULAS()}");
            cs.execute();
            ResultSet rs = cs.getResultSet();

            while(rs.next()){
                Pelicula p = new Pelicula();

                //e.setRut(rs.getInt("rut"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setAnio(rs.getInt("anio"));
                p.setDuracion(rs.getInt("duracion"));
                p.setGenero(rs.getString("genero"));

                lRes.add(p);
            }

            res = lRes.toArray(new Pelicula[0]);
            conn.close();
        }
        catch(SQLException ex) {
            error = "Error al obtener Películas:\n" + ex.toString();
        }

        if(conn != null)
            try { conn.close(); } catch(Exception ex) {};

        return res;
    }

    public boolean agregarPelicula(Pelicula p) {
        error = null;
        Connection conn = null;
        boolean res = false;


        try {
            conn = DB.getInstance().getConnection();
            CallableStatement cs = conn.prepareCall("{call CREAR_PELICULA(?,?,?,?,?,?)}");
            //cs.setInt(1, p.getId_pelicula());
            cs.setString(1, p.getTitulo());
            cs.setString(2, p.getDirector());
            cs.setInt(3, p.getAnio());
            cs.setInt(4, p.getDuracion());
            cs.setString(5, p.getGenero());

            cs.registerOutParameter(6, Types.INTEGER);
            cs.executeUpdate();

            res = true;
            conn.close();
        }
        catch(SQLException ex) {
            error = "Error al agregar película:\n" + ex.toString();
            ex.printStackTrace();
        }

        if(conn != null)
            try { conn.close(); } catch(Exception ex) {};

        return res;
    }


    public boolean modificarPelicula(Pelicula p) {
        error = null;
        Connection conn = null;
        boolean res = false;

        try {
            conn = DB.getInstance().getConnection();
            CallableStatement cs = conn.prepareCall("{call MODIFICAR_PELICULA(?,?,?,?,?)}");
            cs.setString(1, p.getTitulo());
            cs.setString(2, p.getDirector());
            cs.setInt(3, p.getAnio());
            cs.setInt(4, p.getDuracion());
            cs.setString(5, p.getGenero());
            cs.executeUpdate();

            res = true;
            conn.close();
        }
        catch(SQLException ex) {
            error = "Error al modificar película:\n" + ex.toString();
        }

        if(conn != null)
            try { conn.close(); } catch(Exception ex) {};

        return res;
    }

    public boolean eliminarPelicula(Pelicula p) {
        error = null;
        Connection conn = null;
        boolean res = false;

        try {
            conn = DB.getInstance().getConnection();
            CallableStatement cs = conn.prepareCall("{call ELIMINAR_PELICULA(?)}");
            cs.setInt(1, p.getId_pelicula());
            cs.execute();

            res = true;
            conn.close();
        }
        catch(SQLException ex) {
            error = "Error al eliminar película:\n" + ex.toString();
        }

        if(conn != null)
            try { conn.close(); } catch(Exception ex) {};

        return res;
    }
}

