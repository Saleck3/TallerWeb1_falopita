package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class ControladorPerfilTest {

    private ControladorPerfil controladorPerfilParaTest;
    private ServicioPersona servicioPersonaMock;
    private HttpServletRequest requestMock;

    @Before
    public void init(){
        servicioPersonaMock = mock(ServicioPersona.class);
        requestMock = mock(HttpServletRequest.class);
        controladorPerfilParaTest = new ControladorPerfil(servicioPersonaMock);
    }

    @Test
    public void dadoQueNoEstaLogueadoDevolverAlLoginConError(){
        when(requestMock.getSession().getAttribute("ID")).thenReturn(null);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("login");
        assertThat((String) mavDevuelto.getModel().get("error")).isEqualTo("Debe loguerse para usar la aplicación");
    }

    @Test
    public void dadoQueEstaLogueadoDevolverPerfilConDatos(){
        Persona personaMock = new Persona("", "", "", 1,1.0,1.0,'a');
        personaMock.setId(1L);

        when(requestMock.getSession().getAttribute("ID")).thenReturn(1L);
        when(servicioPersonaMock.obtenerPersona(1L)).thenReturn(personaMock);
        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("perfil");
        assertThat((DatosPerfil) mavDevuelto.getModel().get("datosPerfil")).isNotNull();
    }
}
