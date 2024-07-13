package com.challenge.literatura.datos;

import com.challenge.literatura.datos.DatosLibro;
import com.challenge.literatura.datos.Idioma;

public class Libros {
    private String titulo;
    private String autor;
    private Double numeroDescargas;
    private Idioma idioma;

    public Libros() {
    }

    public Libros(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autor = datos.autor();
        this.idioma = Idioma.fromString(datos.idioma().split(",")[0].trim());
        this.numeroDescargas = datos.numeroDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                ", idioma=" + idioma +
                '}';
    }
}
