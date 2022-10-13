package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;

import java.io.Serializable;
import java.util.List;

public interface RepositorioNota {

    Nota obtener(Long id);

    Nota guardar(Nota notaAGuardar);

    void modificar(Nota notaAGuardar);

    void eliminar(Nota notaAEliminar);

    List<Nota> listarTodas();

    List<Nota> listarActivas();

    List<Nota> listarAncladas();

    List<Nota> listarArchivadas();
}
