package com.challenge.literatura.repositorio;

import com.challenge.literatura.datos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAutorByNombre(String nombre);

    @Query("SELECT e FROM Autor e WHERE e.fechaNacimiento <: year AND e.fechaFallecimiento >: year")
    List<Autor> autoresVivosEnUnAño(Long year);

}
