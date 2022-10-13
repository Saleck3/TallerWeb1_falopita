package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.nota.ServicioNota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioNotaImpl implements ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public ServicioNotaImpl() {
    }

    public ServicioNotaImpl(RepositorioNota repositorioNota) {
        this.repositorioNota = repositorioNota;
    }

    @Override
    public Nota guardarNota(Nota notaAGuardar) {
        return repositorioNota.guardar(notaAGuardar);
    }

    @Override
    public void archivarNota(Nota nota) {
        nota.archivar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void desarchivar(Nota nota) {
        nota.desarchivar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void anclarNota(Nota nota) {
        nota.anclar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void desanclar(Nota nota) {
        nota.desanclar();
        repositorioNota.modificar(nota);
    }

    @Override
    public List<Nota> listarNotas() {
        List<Nota> notas = repositorioNota.listarAncladas();
        notas.addAll(repositorioNota.listarActivas());
        return notas;
    }
}
