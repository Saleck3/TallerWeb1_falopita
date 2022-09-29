package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RepositorioPersonaTest extends SpringTest {

    @Autowired
    private RepositorioPersona repositorioPersona;

    @Test
    @Transactional
    @Rollback
    public void dadoUnConjuntoDeDatosDebeCrearUnaPersonaEnLaBaseDeDatosYLosDatosDebenSerLosIndicados() {

        Persona personaCreada = new Persona("Lucas Cardozo", 23, 60.4, 180.0, "M");
        session().save(personaCreada);

        Persona personaObtenida = repositorioPersona.obtener(1L);

        assertThat(personaObtenida).isNotNull();
        assertThat(personaObtenida.getId()).isEqualTo(1L);
        assertThat(personaObtenida.getNombre()).isEqualTo("Lucas Cardozo");
        assertThat(personaObtenida.getEdad()).isEqualTo(23);
        assertThat(personaObtenida.getPeso()).isEqualTo(60.4);
        assertThat(personaObtenida.getSexo()).isEqualTo("M");
    }

    @Test
    @Transactional
    @Rollback
    public void dadaLaCreacionDeDosPersonasEstasSeCreanConIdIncremental() {
        Persona personaCreada1 = new Persona("Nombre 1", 23, 60.4, 150.15, "M");
        Persona personaCreada2 = new Persona("Nombre 2", 23, 60.4, 202.50, "M");

        session().save(personaCreada1);
        session().save(personaCreada2);

        Persona personaObtenida1 = repositorioPersona.obtener(1L);
        Persona personaObtenida2 = repositorioPersona.obtener(2L);

        assertThat(personaObtenida1).isNotNull();
        assertThat(personaObtenida1.getId()).isEqualTo(1L);
        assertThat(personaObtenida2).isNotNull();
        assertThat(personaObtenida2.getId()).isEqualTo(2L);
    }

    @Test
    @Transactional
    @Rollback
    public void dadaLaCreacionDePersonaConIdNoIncrementalEstaSeCreaConIdIncrementalIgualmente() {
        Persona personaCreada = new Persona("Nombre 1", 23, 60.4, 170.70, "M");

        session().save(personaCreada);

        Persona personaObtenida = repositorioPersona.obtener(10L);

        assertThat(personaObtenida).isNull();
    }

}