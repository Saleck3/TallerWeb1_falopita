package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenioImpl;
import ar.edu.unlam.tallerweb1.domain.suenio.EdadNegativaException;
import ar.edu.unlam.tallerweb1.domain.suenio.ValorRecomendado;
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

    @Test(expected = EdadNegativaException.class)
    public void siTengoEdadNegativaTiroExcepcion() throws EdadNegativaException {
        Persona persona = dadoQueTengoUnaPersonaConEdadNegativa();
        servicioSuenio.obtenerCantidadHorasSuenio(persona);
    }


    @Test
    public void queDevuelvaUnTiempoCorrectoSegunPersona() throws EdadNegativaException {
        Persona persona = new Persona("tuvieja@example.com", "poyas123", "tuvieja", 5, 25d, 170.0, 'm');
        ValorRecomendado recomendacion =servicioSuenio.obtenerCantidadHorasSuenio(persona);
        assertThat(recomendacion.getMinimo()).isEqualTo(10d);
        assertThat(recomendacion.getMaximo()).isEqualTo(13d);
    }

    private Persona dadoQueTengoUnaPersona() {
        return new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
    }

    private Persona dadoQueTengoUnaPersonaConEdadNegativa() {
        return new Persona("persona1@example.com", "12345678", "Nombre 1", -1, 60.4, 170.70, 'M');
    }
}
