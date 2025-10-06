package mvc;

import java.util.ArrayList;

public class Pelicula {
    private int id_pelicula;
    private String titulo;
    private String director;
    private int anio;
    private int duracion; //en minutos
    private String genero;

    public Pelicula() {
    }



    public Pelicula(String titulo, String director, int anio, int duracion, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
    }



    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public String[] validar() {
        ArrayList<String> res = new ArrayList<>();

        //if(id_pelicula == 0)
        //    res.add("id no asignada.");

        if(titulo == null)
            res.add("Titulo no asignado.");
        else if(titulo.equals(""))
            res.add("Titulo en blanco.");

        if(director == null)
            res.add("Director no asignado.");
        else if(director.equals(""))
            res.add("Director en blanco.");

        if(anio == 0)
            res.add("Año no asignada.");

        if(duracion == 0)
            res.add("Duracion no asignada.");

        if(genero == null)
            res.add("Genero no asignado.");
        else if(genero.equals(""))
            res.add("Genero en blanco.");

        return res.toArray(new String[0]);
    }
}


