package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.RepositorioPersona;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioPersonaTest extends SpringTest {

    @Autowired
    private RepositorioPersona repositorioPersona;

    @Test
    @Transactional
    @Rollback
    public void dadoUnConjuntoDeDatosDebeCrearUnaPersonaEnLaBaseDeDatosYLosDatosDebenSerLosIndicados(){

        Persona personaCreada = new Persona(1L, "Lucas Cardozo", 23, 60.4, "M");
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
    public void dadaLaCreacionDeDosPersonasEstasSeCreanConIdIncremental(){
        Persona personaCreada1 = new Persona(1L, "Nombre 1", 23, 60.4, "M");
        Persona personaCreada2 = new Persona(56L,"Nombre 2", 23, 60.4, "M");

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
    public void dadaLaCreacionDePersonaConIdNoIncrementalEstaSeCreaConIdIncrementalIgualmente(){
        Persona personaCreada = new Persona(10L, "Nombre 1", 23, 60.4, "M");

        session().save(personaCreada);

        Persona personaObtenida = repositorioPersona.obtener(10L);

        assertThat(personaObtenida).isNotNull();
        assertThat(personaObtenida.getId()).isEqualTo(10L);
    }


    @Test
    @Transactional
    @Rollback
    public void corroborarQueSeCalculenLasHorasDeSuenioCorrectamentePorEdad(){
        //Preparacion
        Persona personaPrueba = new Persona(1L, "Nahuel Rolon", 25, 70.0, "M");
        session().save(personaPrueba);
        Suenio tiempoSuenio = new Suenio();


        //Ejecucion
        Persona personaTraida = repositorioPersona.obtener(1L);
        Integer horaCalculada = tiempoSuenio.obtenerCantidadHorasSuenio(personaTraida.getEdad());

        //Verificacion
        assertThat(personaTraida.calcularHoraSuenio(personaTraida.getEdad())).isEqualTo(horaCalculada);
    }
}