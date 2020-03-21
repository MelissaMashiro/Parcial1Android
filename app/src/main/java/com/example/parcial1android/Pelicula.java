package com.example.parcial1android;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Pelicula implements Parcelable {

    private String nombre;
    private String director;
    private String idioma;
    private String genero;

    public Pelicula(String nombre, String director, String idioma, String genero){
        this.nombre = nombre;
        this.director = director;
        this.idioma = idioma;
        this.genero = genero;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    protected Pelicula(Parcel in) {
        nombre = in.readString();
        director = in.readString();
        idioma = in.readString();
        genero = in.readString();
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "nombre='" + nombre + '\'' +
                ", director='" + director + '\'' +
                ", idioma='" + idioma + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(director);
        dest.writeString(idioma);
        dest.writeString(genero);
    }
}
