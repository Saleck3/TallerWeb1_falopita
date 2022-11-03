package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.suenio.NoTieneRegistroDeSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ControladorSuenioTest {

    ControladorSuenio controladorSuenio;

    @Mock
    ServicioSuenio servicioSuenio;
    @Mock
    ServicioPersona servicioPersona;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controladorSuenio = new ControladorSuenio(servicioPersona, servicioSuenio);
    }

    @Test
    public void querCuandoTraigaMetricasMeDeElString() throws NoTieneRegistroDeSuenio {
        Persona persona = dadoQueTengoUnaPersona();
        List<RegistroSuenio> metricas = dadoQueTengoRegistros(persona);
        String metrica = cuandoPidoLasMetricas(persona, metricas);
        entoncesObtengoElString("2,2,2", metrica);
    }

    private String cuandoPidoLasMetricas(Persona persona, List<RegistroSuenio> metricas) throws NoTieneRegistroDeSuenio {
        when(servicioSuenio.registrosDeLosUltimosxDias(persona, 7)).thenReturn(metricas);
        return controladorSuenio.metricaDeHorasEnLaUltimaSemana(persona);
    }

    private void entoncesObtengoElString(String esperado, String metrica) {
        assertThat(metrica).isEqualTo(esperado);
    }


    private List<RegistroSuenio> dadoQueTengoRegistros(Persona persona) {
        List<RegistroSuenio> metricas = new ArrayList<>();
        LocalDateTime horaInicio = LocalDateTime.now();
        LocalDateTime horaFin = LocalDateTime.now().plus(2, ChronoUnit.HOURS);

        metricas.add(new RegistroSuenio(persona, horaInicio, horaFin));
        metricas.add(new RegistroSuenio(persona, horaInicio, horaFin));
        metricas.add(new RegistroSuenio(persona, horaInicio, horaFin));
        return metricas;
    }

    private Persona dadoQueTengoUnaPersona() {
        return new Persona("persona@example.com", "1234567894", "Nombre 5", 23, 60.4, 170.70, 'm');
    }
}
