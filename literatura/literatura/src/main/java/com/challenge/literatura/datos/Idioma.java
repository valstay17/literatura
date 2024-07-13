package com.challenge.literatura.datos;

public enum Idioma {
    ESPAÑOL("es", "español"),
    INGLES("en", "inglés"),
    FRANCES("fr", "frances"),
    PORTUGUES("pt", "portugués");

    private String idiomaOmdb;
    private String idiomaEspañol;

    Idioma(String idiomaOmdb, String idiomaEspañol) {
        this.idiomaOmdb = idiomaOmdb;
        this.idiomaEspañol = idiomaEspañol;
    }

    public static Idioma fromString(String text){
        for(Idioma idioma : Idioma.values()){
            if(idioma.idiomaOmdb.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma fue encontrado: " + text);
    }

    public static Idioma fromEspañol(String text){
        for(Idioma idioma : Idioma.values()){
            if(idioma.idiomaEspañol.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma fue encontrado: " + text);
    }
}
