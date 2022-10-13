package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.nota.ServicioNota;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioNota;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.mockito.Mockito.when;

public class ServicioNotaTest {

    @Mock
    private RepositorioNota repositorioNotaMock;

    private ServicioNota servicioNota;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        servicioNota = new ServicioNotaImpl(repositorioNotaMock);
    }

    @Test
    public void cuandoCambioElEstadoDeUnaNotaSeActualiza() {
        Nota nota = dadoQueTengoUnaNotaActiva();
        cuandoCambioElEstadoAArchivada(nota);
        entoncesElEstadoEs(nota, Nota.estadosPosibles.ARCHIVADO);
    }

    @Test
    public void cuandoListoLasNotasSeListanPrimeroLasAncladas() {
        Nota notaActiva = dadoQueTengoUnaNotaActiva();
        Nota notaAnclada = dadoQueTengoUnaNotaAnclada();
        List<Nota> notas = cuandoListoTodasLasNotas(notaActiva,notaAnclada);
        entoncesSalenPrimeroLasNotasAncladas(notas);
    }




    private Nota dadoQueTengoUnaNotaActiva() {
        Nota nota = new Nota("Titulo", "Descripcion");
        when(repositorioNotaMock.guardar(nota)).thenReturn(nota);
        return servicioNota.guardarNota(nota);
    }

    private Nota dadoQueTengoUnaNotaAnclada() {
        Nota nota = new Nota("Titulo", "Descripcion");
        nota.anclar();
        when(repositorioNotaMock.guardar(nota)).thenReturn(nota);
        return servicioNota.guardarNota(nota);
    }

    private void cuandoCambioElEstadoAArchivada(Nota nota) {
        servicioNota.archivarNota(nota);
    }

    private List<Nota> cuandoListoTodasLasNotas(Nota notaActiva, Nota notaAnclada) {
        List<Nota> notasActivas = new LinkedList<Nota>();
        notasActivas.add(notaActiva);
        when(repositorioNotaMock.listarActivas()).thenReturn(notasActivas);

        List<Nota> notasAncladas = new LinkedList<Nota>();
        notasAncladas.add(notaAnclada);

        when(repositorioNotaMock.listarAncladas()).thenReturn(notasAncladas);
        return servicioNota.listarNotas();
    }

    private void entoncesElEstadoEs(Nota nota, Nota.estadosPosibles archivado) {
        assertThat(nota.getEstado()).isEqualTo(archivado);
    }

    private void entoncesSalenPrimeroLasNotasAncladas(List<Nota> notas) {
        assertThat(notas.get(0).getEstado()).isEqualTo(Nota.estadosPosibles.ANCLADO);
        assertThat(notas.get(1).getEstado()).isEqualTo(Nota.estadosPosibles.ACTIVO);
    }

}
