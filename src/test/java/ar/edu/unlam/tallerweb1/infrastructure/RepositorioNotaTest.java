package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.nota.RepositorioNota;
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
        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);

        List<Nota> notas = cuandoBuscoNotas(persona);

        entoncesObtengoNotas(notas, 2);
    }

    @Test
    public void dadasVariasNotasConAlgunasArchivadasObtenemosLaCantidadArchivada() {
        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        dadoQueTengoNotaArchivada(persona);
        dadoQueTengoNotaArchivada(persona);
        dadoQueTengoNotaArchivada(persona);

        List<Nota> notasTotales = cuandoBuscoNotas(persona);
        List<Nota> notasArchivadas = cuandoBuscoNotasArchivadas(persona);

        entoncesObtengoNotas(notasTotales, 5);
        entoncesObtengoNotas(notasArchivadas, 3);
    }

    @Test
    public void dadasVariasNotasConAlgunasAncladasObtenemosLaCantidadAncladas() {
        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        dadoQueTengoNotaAnclada(persona);
        dadoQueTengoNotaAnclada(persona);


        List<Nota> notasTotales = cuandoBuscoNotas(persona);
        List<Nota> notasAncladas = cuandoBuscoNotasAncladas(persona);

        entoncesObtengoNotas(notasTotales, 5);
        entoncesObtengoNotas(notasAncladas, 2);
    }

    @Test
    public void dadasVariasNotasConLosTresEstadosObtengoSoloLasActivas() {

        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        dadoQueTengoNotaAnclada(persona);
        dadoQueTengoNotaAnclada(persona);
        dadoQueTengoNotaArchivada(persona);
        dadoQueTengoNotaArchivada(persona);


        List<Nota> notasTotales = cuandoBuscoNotas(persona);
        List<Nota> notasActivas = cuandoBuscoNotasActivas(persona);

        entoncesObtengoNotas(notasTotales, 7);
        entoncesObtengoNotas(notasActivas, 3);
    }


    @Test
    public void dadasNotasCuandoEliminoNotasObtenemosLaCantidadCorrecta() {

        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona);
        Nota notaAEliminar = dadoQueTengoNota(persona);

        dadoQueEliminoNota(notaAEliminar);

        List<Nota> notasTotales = cuandoBuscoNotas(persona);

        entoncesObtengoNotas(notasTotales, 2);
    }

    @Test
    public void dadoQueTengoNotasDeDistintasPersonasTraigoSoloDeLaPersonaSolicitada() {

        Persona persona = new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        Persona persona2 = new Persona("persona2@example.com", "12345678", "Nombre 2", 23, 60.4, 170.70, 'M');

        dadoQueTengoNota(persona);
        dadoQueTengoNota(persona2);

        List<Nota> notas = cuandoBuscoNotas(persona);

        entoncesNotasSonDePersona(notas, persona);
    }


    private Nota dadoQueTengoNota(Persona persona) {
        Nota nota = new Nota(persona, "Titulo", "Descripcion");
        respositorioNota.guardar(nota);
        return nota;
    }


    private Nota dadoQueTengoNotaArchivada(Persona persona) {
        Nota nota = new Nota(persona, "Titulo", "Descripcion");
        nota.archivar();
        respositorioNota.guardar(nota);
        return nota;
    }

    private Nota dadoQueTengoNotaAnclada(Persona persona) {
        Nota nota = new Nota(persona, "Titulo", "Descripcion");
        nota.anclar();
        respositorioNota.guardar(nota);
        return nota;
    }


    private void dadoQueEliminoNota(Nota notaAEliminar) {
        respositorioNota.eliminar(notaAEliminar);
    }

    private List<Nota> cuandoBuscoNotas(Persona persona) {
        return respositorioNota.listarTodas(persona);
    }

    private List<Nota> cuandoBuscoNotasActivas(Persona persona) {
        return respositorioNota.listarActivas(persona);
    }

    private List<Nota> cuandoBuscoNotasArchivadas(Persona persona) {
        return respositorioNota.listarArchivadas(persona);
    }

    private List<Nota> cuandoBuscoNotasAncladas(Persona persona) {
        return respositorioNota.listarAncladas(persona);
    }

    private void entoncesObtengoNotas(List<Nota> notasObtenidas, int cantidadEsperada) {
        assertThat(notasObtenidas.size()).isEqualTo(cantidadEsperada);
    }

    private void entoncesNotasSonDePersona(List<Nota> notas, Persona persona) {

        for (Nota nota : notas) {
            assertThat(nota.getPersona()).isEqualTo(persona);
        }

    }
}
