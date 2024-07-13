package com.challenge.literatura.principal;

import com.challenge.literatura.datos.Autor;
import com.challenge.literatura.datos.DatosLibro;
import com.challenge.literatura.datos.Idioma;
import com.challenge.literatura.datos.Libros;
import com.challenge.literatura.repositorio.AutorRepository;
import com.challenge.literatura.repositorio.LibroRepository;
import com.challenge.literatura.services.ConsumoAPI;
import com.challenge.literatura.services.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private List<DatosLibro> datosLibro = new ArrayList<>();
    private List<Libros> libros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private Optional<Libros> libroBuscado;
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository repositorio, AutorRepository autorRepository) {
        this.repositorio = repositorio;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    --------------------
                    Challenge - Literatura
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Exit
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivosEnUnAño();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;

            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que desea buscar.");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosLibro datos = convierteDatos.obtenerDatos(json, DatosLibro.class);
        return  datos;
    }

    private void librosPorIdioma() {
        System.out.println("Escribe el idioma del libro que desea buscar.");
        var idiomas = """
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """;
        System.out.println(idiomas);
        var nombreIdioma = teclado.nextLine();
        var idioma = Idioma.fromEspañol(nombreIdioma);
        List<Libros> libroPorIdioma = repositorio.findByIdioma(idioma);
        System.out.println("Los libros registrados con el idioma " + nombreIdioma + " son los siguientes");
        libroPorIdioma.forEach(System.out::println);
    }

    private void autoresVivosEnUnAño() {
        System.out.println("Ingrese el año a buscar los autores en esa fecha ");
        var year = teclado.nextLine();
        autores = autorRepository.findAll();
        if(autores != null){
            autores = autorRepository.autoresVivosEnUnAño(Long.valueOf(year));
            autores.forEach(System.out::println);
        } else {
            System.out.println("No se encontró autores en esa fecha.");
        }
    }

    private void autoresRegistrados() {
        DatosLibro datos = getDatosLibro();
        Autor autor = new Autor();
        autorRepository.save(autor);
        System.out.println(datos);
    }

    private void librosRegistrados() {
    libros = repositorio.findAll();
    libros.stream()
            .sorted(Comparator.comparing(Libros::getIdioma))
            .forEach(System.out::println);
    }

    private void buscarLibroPorTitulo() {
        buscarLibro();
        System.out.println("Repita el titulo a buscar");
        var nombreLibro = teclado.nextLine();
        libroBuscado = repositorio.findByTituloContainsIgnoreCase(nombreLibro);
        if(libroBuscado.isPresent()) {
            System.out.println("El libro buscado es: " + libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void buscarLibro(){
        DatosLibro datos = getDatosLibro();
        Libros libro = new Libros(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }
}
