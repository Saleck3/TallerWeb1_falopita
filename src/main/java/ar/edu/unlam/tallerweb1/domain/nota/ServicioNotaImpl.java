package ar.edu.unlam.tallerweb1.domain.nota;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.nota.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioNotaImpl implements ServicioNota {

    @Autowired
    private RepositorioNota repositorioNota;

    public ServicioNotaImpl() {
    }

    public ServicioNotaImpl(RepositorioNota repositorioNota) {
        this.repositorioNota = repositorioNota;
    }

    @Override
    public Nota guardar(Nota notaAGuardar) {
        return repositorioNota.guardar(notaAGuardar);
    }

    @Override
    public Nota obtener(Long id) {
        return repositorioNota.obtener(id);
    }

    @Override
    public void archivar(Nota nota) {
        nota.archivar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void desarchivar(Nota nota) {
        nota.desarchivar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void anclar(Nota nota) {
        nota.anclar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void desanclar(Nota nota) {
        nota.desanclar();
        repositorioNota.modificar(nota);
    }

    @Override
    public void eliminar(Nota nota) {
        repositorioNota.eliminar(nota);
    }

    @Override
    public List<Nota> listar(Persona persona) {
        List<Nota> notas = repositorioNota.listarAncladas(persona);
        notas.addAll(repositorioNota.listarActivas(persona));
        return notas;
    }

    @Override
    public List<Nota> listarArchivadas(Persona persona) {
        return repositorioNota.listarArchivadas(persona);
    }
}
