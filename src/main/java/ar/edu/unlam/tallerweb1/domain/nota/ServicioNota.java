package ar.edu.unlam.tallerweb1.domain.nota;

import java.util.List;

public interface ServicioNota {
    Nota guardarNota(Nota notaAGuardar);

    void archivarNota(Nota nota);

    void desarchivar(Nota nota);

    void anclarNota(Nota nota);

    void desanclar(Nota nota);

    List<Nota> listarNotas();
}
