package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonaTest {

    @Test
    public void dadaCreacionDeUnaPersonaSeCreaConIdNulo(){
        Long idEsperado = null;
        Persona personaCreada =  new Persona();

        Long idObtenido = personaCreada.getId();

        assertThat(idObtenido).isEqualTo(idEsperado);
    }
}
