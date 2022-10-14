package ar.edu.unlam.tallerweb1.domain.nota;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.List;

public interface ServicioNota {
    Nota guardar(Nota notaAGuardar);

    Nota obtener(Long id);

    void archivar(Nota nota);

    void desarchivar(Nota nota);

    void anclar(Nota nota);

    void desanclar(Nota nota);
    void eliminar(Nota nota);

    List<Nota> listar(Persona persona);
    List<Nota> listarArchivadas(Persona persona);
}
