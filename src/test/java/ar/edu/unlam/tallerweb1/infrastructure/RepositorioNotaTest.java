package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback
public class RepositorioNotaTest extends SpringTest {

    @Autowired
    private RepositorioNota respositorioNota;

    @Test
    public void dadasVariasNotasObtenemosLaListaCompleta() {
        dadoQueTengoNota();
        dadoQueTengoNota();

        List<Nota> notas = cuandoBuscoNotas();

        entoncesObtengoNotas(notas, 2);
    }

    @Test
    public void dadasVariasNotasConAlgunasArchivadasObtenemosLaCantidadArchivada() {
        dadoQueTengoNota();
        dadoQueTengoNota();
        dadoQueTengoNotaArchivada();
        dadoQueTengoNotaArchivada();
        dadoQueTengoNotaArchivada();

        List<Nota> notasTotales = cuandoBuscoNotas();
        List<Nota> notasArchivadas = cuandoBuscoNotasArchivadas();

        entoncesObtengoNotas(notasTotales, 5);
        entoncesObtengoNotas(notasArchivadas, 3);
    }

    @Test
    public void dadasVariasNotasConAlgunasAncladasObtenemosLaCantidadAncladas() {
        dadoQueTengoNota();
        dadoQueTengoNota();
        dadoQueTengoNota();
        dadoQueTengoNotaAnclada();
        dadoQueTengoNotaAnclada();


        List<Nota> notasTotales = cuandoBuscoNotas();
        List<Nota> notasAncladas = cuandoBuscoNotasAncladas();

        entoncesObtengoNotas(notasTotales, 5);
        entoncesObtengoNotas(notasAncladas, 2);
    }

    @Test
    public void dadasVariasNotasConLosTresEstadosObtengoSoloLasActivas() {
        dadoQueTengoNota();
        dadoQueTengoNota();
        dadoQueTengoNota();
        dadoQueTengoNotaAnclada();
        dadoQueTengoNotaAnclada();
        dadoQueTengoNotaArchivada();
        dadoQueTengoNotaArchivada();


        List<Nota> notasTotales = cuandoBuscoNotas();
        List<Nota> notasActivas = cuandoBuscoNotasActivas();

        entoncesObtengoNotas(notasTotales, 7);
        entoncesObtengoNotas(notasActivas, 3);
    }


    @Test
    public void dadasNotasCuandoEliminoNotasObtenemosLaCantidadCorrecta() {
        dadoQueTengoNota();
        dadoQueTengoNota();
        Nota notaAEliminar = dadoQueTengoNota();

        dadoQueEliminoNota(notaAEliminar);

        List<Nota> notasTotales = cuandoBuscoNotas();

        entoncesObtengoNotas(notasTotales, 2);
    }


    private Nota dadoQueTengoNota() {
        Nota nota = new Nota("Titulo", "Descripcion");
        respositorioNota.guardar(nota);
        return nota;
    }


    private Nota dadoQueTengoNotaArchivada() {
        Nota nota = new Nota("Titulo", "Descripcion");
        nota.archivar();
        respositorioNota.guardar(nota);
        return nota;
    }

    private Nota dadoQueTengoNotaAnclada() {
        Nota nota = new Nota("Titulo", "Descripcion");
        nota.anclar();
        respositorioNota.guardar(nota);
        return nota;
    }

    private void dadoQueEliminoNota(Nota notaAEliminar) {
        respositorioNota.eliminar(notaAEliminar);
    }
    private List<Nota> cuandoBuscoNotas() {
        return respositorioNota.listarTodas();
    }

    private List<Nota> cuandoBuscoNotasActivas() {
        return respositorioNota.listarActivas();
    }

    private List<Nota> cuandoBuscoNotasArchivadas() {
        return respositorioNota.listarArchivadas();
    }

    private List<Nota> cuandoBuscoNotasAncladas() {
        return respositorioNota.listarAncladas();
    }

    private void entoncesObtengoNotas(List<Nota> notasObtenidas, int cantidadEsperada) {
        assertThat(notasObtenidas.size()).isEqualTo(cantidadEsperada);
    }
}
