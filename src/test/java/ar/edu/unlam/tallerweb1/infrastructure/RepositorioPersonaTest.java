package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.hibernate.PropertyValueException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//estas anotaciones aplican para todos los tests
@Transactional
@Rollback
public class RepositorioPersonaTest extends SpringTest {

    @Autowired
    private RepositorioPersona repositorioPersona;

    /* -- intento de precargar los datos usando @BeforeClass pero se ejecuta para cada test
    @BeforeClass
    public static void init(){
        Persona persona1 = new Persona("Nombre 1", 23, 60.4, 170.70, "M");
        Persona persona2 = new Persona("Nombre 2", 23, 60.4, 170.70, "M");
        Persona persona3 = new Persona("Nombre 3", 23, 60.4, 170.70, "M");
        Persona persona4 = new Persona("Nombre 4", 23, 60.4, 170.70, "M");

        repositorioPersona.guardar(persona1);
        repositorioPersona.guardar(persona2);
        repositorioPersona.guardar(persona3);
        repositorioPersona.guardar(persona4);
    }
    */

    @Test(expected = Exception.class)
    public void dadoPersonaConAtributosNulosArrojaExcepcionCuandoSePersiste(){
        Persona personaCreada = new Persona();

        repositorioPersona.guardar(personaCreada);
    }

    @Test
    public void dadoDatosSePersistePersonaYDevuelveIdCreado() {
        Long idEsperado = 5L;
        String nombre = "Lucas Cardozo";
        Integer edad = 23;
        Double peso = 60.4;
        Double altura = 180.0;
        String sexo = "M";

        dadoQueTengoPersonasEnLaBaseDeDatos();

        Persona personaCreada = new Persona(nombre, edad, peso, altura, sexo);

        Long idAsignada = (Long) repositorioPersona.guardar(personaCreada);

        assertThat(idAsignada).isNotNull();
        assertThat(idAsignada).isEqualTo(idEsperado);
    }
    @Transactional
    @Rollback
    @Test
    public void dadoIdDevuelveObjetoPersonaYSusDatos(){
        dadoQueTengoPersonasEnLaBaseDeDatos();

        Long idDada = 1L;
        String nombreEsperado = "Nombre 1";
        Integer edadEsperada = 23;
        Double pesoEsperado = 60.4;
        Double alturaEsperada = 170.7;
        String sexoEsperado = "M";

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

        dadoQueTengoPersonasEnLaBaseDeDatos();

        Persona personaObtenida1 = repositorioPersona.obtener(primerIdEsperado);
        Persona personaObtenida2 = repositorioPersona.obtener(segundoIdEsperado);

        assertThat(personaObtenida1.getNombre()).isEqualTo("Nombre 1");
        assertThat(personaObtenida2.getNombre()).isEqualTo("Nombre 2");
    }

    /*
      no considero que esto esté correcto, pues si la idea es probar de forma unitaria
      cada metodo, aca se esta haciendo uso de un metodo que no necesariamente tiene
      que "funcionar", entonces se están probando varias cosas cada vez que se llama
      a este mismo
      esto se puede evitar teniendo una base de datos local y no en memoria
      puesto que la mencionada tendria datos precargados para hacer los tests
    */

    private void dadoQueTengoPersonasEnLaBaseDeDatos(){
        Persona persona1 = new Persona("Nombre 1", 23, 60.4, 170.70, "M");
        Persona persona2 = new Persona("Nombre 2", 23, 60.4, 170.70, "M");
        Persona persona3 = new Persona("Nombre 3", 23, 60.4, 170.70, "M");
        Persona persona4 = new Persona("Nombre 4", 23, 60.4, 170.70, "M");

        repositorioPersona.guardar(persona1);
        repositorioPersona.guardar(persona2);
        repositorioPersona.guardar(persona3);
        repositorioPersona.guardar(persona4);
    }
}