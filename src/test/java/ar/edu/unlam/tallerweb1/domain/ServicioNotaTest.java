package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.nota.ServicioNota;
import ar.edu.unlam.tallerweb1.domain.nota.ServicioNotaImpl;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.nota.RepositorioNota;
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
        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        Nota nota = dadoQueTengoUnaNotaActiva(persona);
        cuandoCambioElEstadoAArchivada(nota);
        entoncesElEstadoEs(nota, Nota.estadosPosibles.ARCHIVADO);
    }

    @Test
    public void cuandoListoLasNotasSeListanPrimeroLasAncladas() {
        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        Nota notaActiva = dadoQueTengoUnaNotaActiva(persona);
        Nota notaAnclada = dadoQueTengoUnaNotaAnclada(persona);
        List<Nota> notas = cuandoListoTodasLasNotas(persona, notaActiva, notaAnclada);
        entoncesSalenPrimeroLasNotasAncladas(notas);
    }


    private Nota dadoQueTengoUnaNotaActiva(Persona persona) {
        Nota nota = new Nota(persona, "Titulo", "Descripcion");
        when(repositorioNotaMock.guardar(nota)).thenReturn(nota);
        return servicioNota.guardar(nota);
    }

    private Nota dadoQueTengoUnaNotaAnclada(Persona persona) {
        Nota nota = new Nota(persona, "Titulo", "Descripcion");
        nota.anclar();
        when(repositorioNotaMock.guardar(nota)).thenReturn(nota);
        return servicioNota.guardar(nota);
    }

    private void cuandoCambioElEstadoAArchivada(Nota nota) {
        servicioNota.archivar(nota);
    }

    private List<Nota> cuandoListoTodasLasNotas(Persona persona, Nota notaActiva, Nota notaAnclada) {
        List<Nota> notasActivas = new LinkedList<Nota>();
        notasActivas.add(notaActiva);
        when(repositorioNotaMock.listarActivas(persona)).thenReturn(notasActivas);

        List<Nota> notasAncladas = new LinkedList<Nota>();
        notasAncladas.add(notaAnclada);

        when(repositorioNotaMock.listarAncladas(persona)).thenReturn(notasAncladas);
        return servicioNota.listar(persona);
    }

    private void entoncesElEstadoEs(Nota nota, Nota.estadosPosibles archivado) {
        assertThat(nota.getEstado()).isEqualTo(archivado);
    }

    private void entoncesSalenPrimeroLasNotasAncladas(List<Nota> notas) {
        assertThat(notas.get(0).getEstado()).isEqualTo(Nota.estadosPosibles.ANCLADO);
        assertThat(notas.get(1).getEstado()).isEqualTo(Nota.estadosPosibles.ACTIVO);
    }

}
