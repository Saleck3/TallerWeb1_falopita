package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.ServicioRecordatorio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class ControladorRecordatorioTest {

    private ControladorRecordatorio controladorRecordatorio;

    @Mock
    private ServicioRecordatorio servicioRecordatorioMock;

    @Mock
    private ServicioPersona servicioPersonaMock;

    @Mock
    private HttpServletRequest requestMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        controladorRecordatorio = new ControladorRecordatorio(servicioPersonaMock, servicioRecordatorioMock);
    }

    /*
    @Test
    public void cuandoLlamoAMostrarRecordatoriosMeDevuelveLaVistaRecordatorios(){
        ModelAndView mavDevuelto = controladorRecordatorio.irARecordatorios(requestMock);
        assertThat(mavDevuelto.getViewName()).isEqualTo("recordatorios");
    }
    */
}
