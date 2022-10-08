package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class ControladorPerfilTest extends SpringTest {

    private ControladorPerfil controladorPerfilParaTest;
    @Mock
    ServicioPersona servicioPersonaMock;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controladorPerfilParaTest = new ControladorPerfil(servicioPersonaMock);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void dadoQueNoEstaLogueadoDevolverAlLoginConError() {

        //Esto da NullPointerException
        //when(requestMock.getSession().getAttribute("ID")).thenReturn(null);


        when(session.getAttribute("ID")).thenReturn(null);


        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(request);

        assertThat(mavDevuelto.getViewName()).isEqualTo("login");
        assertThat((String) mavDevuelto.getModel().get("error")).isEqualTo("Debe loguerse para usar la aplicación");
    }

    @Test
    public void dadoQueEstaLogueadoDevolverPerfilConDatos() {
        Persona personaMock = new Persona("", "", "", 1, 1.0, 1.0, 'a');
        personaMock.setId(1L);


        when(session.getAttribute("ID")).thenReturn(1L);

        when(servicioPersonaMock.obtenerPersona(anyLong())).thenReturn(personaMock);
        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(request);

        assertThat(mavDevuelto.getViewName()).isEqualTo("perfil");
        assertThat((DatosPerfil) mavDevuelto.getModel().get("datosPerfil")).isNotNull();
    }
}
