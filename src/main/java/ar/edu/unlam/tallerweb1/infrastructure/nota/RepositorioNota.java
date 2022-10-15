package ar.edu.unlam.tallerweb1.infrastructure.nota;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.io.Serializable;
import java.util.List;

public interface RepositorioNota {

    Nota obtener(Long id);

    Nota guardar(Nota notaAGuardar);

    void modificar(Nota notaAGuardar);

    void eliminar(Nota notaAEliminar);

    List<Nota> listarTodas(Persona persona);

    List<Nota> listarActivas(Persona persona);

    List<Nota> listarAncladas(Persona persona);

    List<Nota> listarArchivadas(Persona persona);
}
