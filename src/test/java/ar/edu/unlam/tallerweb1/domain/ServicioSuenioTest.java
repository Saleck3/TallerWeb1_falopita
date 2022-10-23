package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenioImpl;
import ar.edu.unlam.tallerweb1.infrastructure.suenio.RepositorioSuenio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ServicioSuenioTest extends SpringTest {

    @Mock
    private RepositorioSuenio repositorioSuenio;

    private ServicioSuenio servicioSuenio;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        servicioSuenio = new ServicioSuenioImpl(repositorioSuenio);
    }

    @Test
    public void queDevuelvaUnTiempoCorrectoSegunEdad() {
        Persona persona = dadoQueTengoUnaPersona();

        when(repositorioSuenio.obtener(persona)).thenReturn(null);
        try {
            assertThat(servicioSuenio.obtenerCantidadHorasSuenio(persona).getMinimo()).isEqualTo(7d);
            assertThat(servicioSuenio.obtenerCantidadHorasSuenio(persona).getMaximo()).isEqualTo(9d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = Exception.class)
    public void siTengoEdadNegativaTiroExcepcion() throws Exception {
        Persona persona = dadoQueTengoUnaPersonaConEdadNegativa();
        servicioSuenio.obtenerCantidadHorasSuenio(persona);
    }


    @Test
    public void queDevuelvaUnTiempoCorrectoSegunPersona() {
        Persona persona = new Persona("tuvieja@example.com", "poyas123", "tuvieja", 5, 25d, 170.0, 'm');
        try {
            assertThat(servicioSuenio.obtenerCantidadHorasSuenio(persona).getMinimo()).isEqualTo(10d);
            assertThat(servicioSuenio.obtenerCantidadHorasSuenio(persona).getMaximo()).isEqualTo(13d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Persona dadoQueTengoUnaPersona() {
        return new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
    }

    private Persona dadoQueTengoUnaPersonaConEdadNegativa() {
        return new Persona("persona1@example.com", "12345678", "Nombre 1", -1, 60.4, 170.70, 'M');
    }
}
