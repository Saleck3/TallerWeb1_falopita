package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//estas anotaciones aplican para todos los tests
@Transactional
@Rollback
public class RepositorioPersonaTest extends SpringTest {

    @Autowired
    private RepositorioPersona repositorioPersona;

    //  intento de precargar los datos usando @BeforeClass pero se ejecuta para cada test
    @Before
    public void init(){
        Persona persona1 = new Persona("persona1@example.com","12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
        Persona persona2 = new Persona("persona2@example.com","12345678","Nombre 2", 23, 60.4, 170.70, 'M');
        Persona persona3 = new Persona("persona3@example.com","12345678","Nombre 3", 23, 60.4, 170.70, 'M');
        Persona persona4 = new Persona("persona4@example.com","12345678","Nombre 4", 23, 60.4, 170.70, 'M');

        repositorioPersona.guardar(persona1);
        repositorioPersona.guardar(persona2);
        repositorioPersona.guardar(persona3);
        repositorioPersona.guardar(persona4);
    }


    @Test(expected = Exception.class)
    public void dadoPersonaConAtributosNulosArrojaExcepcionCuandoSePersiste(){
        Persona personaCreada = new Persona();

        repositorioPersona.guardar(personaCreada);
    }

    @Test
    public void dadoIdDevuelveObjetoPersonaYSusDatos(){
        Long idDada = 1L;
        String nombreEsperado = "Nombre 1";
        Integer edadEsperada = 23;
        Double pesoEsperado = 60.4;
        Double alturaEsperada = 170.7;
        Character sexoEsperado = 'M';

        Persona personaObtenida = repositorioPersona.obtener(idDada);

        assertThat(personaObtenida.getNombre()).isEqualTo(nombreEsperado);
        assertThat(personaObtenida.getEdad()).isEqualTo(edadEsperada);
        assertThat(personaObtenida.getPeso()).isEqualTo(pesoEsperado);
        assertThat(personaObtenida.getAltura()).isEqualTo(alturaEsperada);
        assertThat(personaObtenida.getSexo()).isEqualTo(sexoEsperado);
    }


    @Test
    public void dadaCreacionDeDosPersonasSePersistenConIdIncremental() {
        Long primerIdEsperado = 1L;
        Long segundoIdEsperado = 2L;

        Persona personaObtenida1 = repositorioPersona.obtener(primerIdEsperado);
        Persona personaObtenida2 = repositorioPersona.obtener(segundoIdEsperado);

        assertThat(personaObtenida1.getNombre()).isEqualTo("Nombre 1");
        assertThat(personaObtenida2.getNombre()).isEqualTo("Nombre 2");
    }
}