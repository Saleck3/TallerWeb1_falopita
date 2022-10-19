package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ControladorCalendarioTest {

    private ControladorCalendario controladorCalendario;

    @Mock
    ServicioPersona servicioPersona;
    @Mock
    HttpServletRequest requestMock;
    @Mock
    HttpSession sessionMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controladorCalendario = new ControladorCalendario(servicioPersona);
        when(requestMock.getSession()).thenReturn(sessionMock);
    }

    @Test
    public void dadoQueUnaPersonaNoEstaLogueadaDevolverALoginConError(){
        dadoQueObtengoUnIdDeLaSesion(null);
        ModelAndView mavDevuelto = controladorCalendario.irAAviso(requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("login");
        assertThat((String) mavDevuelto.getModel().get("error")).isNotNull();
    }

    private void dadoQueObtengoUnIdDeLaSesion(Long id) {when(sessionMock.getAttribute("ID")).thenReturn(id);
    }

}
