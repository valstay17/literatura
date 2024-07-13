package com.challenge.literatura.repositorio;

import com.challenge.literatura.datos.Idioma;
import com.challenge.literatura.datos.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface LibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);
    List<Libros> findByIdioma(Idioma idioma);
}
