package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioPersonaImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonaTest {

    private ServicioPersona servicioPersona = new ServicioPersonaImpl();


    @Test
    public void dadaCreacionDeUnaPersonaSeCreaConIdNulo(){
        Long idEsperado = null;
        Persona personaCreada =  new Persona();

        Long idObtenido = personaCreada.getId();

        assertThat(idObtenido).isEqualTo(idEsperado);
    }

    @Test
    public void queSePuedaValidarLosDatosDeUnaPersona(){
        Persona personaValida = dadaUnaPersonaConDatosValidos();
        assertThat(cuandoLaPersonaSevalidaLaPersona(personaValida)).isTrue();
    }

    @Test
    public void queNOSePuedaValidarLosDatosDeUnaPersonaInvalida(){
        //Aca se podria hacer un test por cada valor pero me parece al pedo
        Persona personaInvalida = dadaUnaPersonaConDatosInvalidos();
        assertThat(cuandoLaPersonaSevalidaLaPersona(personaInvalida)).isFalse();
    }

    private Persona dadaUnaPersonaConDatosInvalidos() {
        return new Persona("","", "", 200, 560.4, 370.70, 'a');

    }

    private Persona dadaUnaPersonaConDatosValidos() {
        return new Persona("persona@example.com","1234567894", "Nombre 5", 23, 60.4, 170.70, 'm');
    }

    private boolean cuandoLaPersonaSevalidaLaPersona(Persona persona) {
        return servicioPersona.validarPersona(persona);
    }


}
