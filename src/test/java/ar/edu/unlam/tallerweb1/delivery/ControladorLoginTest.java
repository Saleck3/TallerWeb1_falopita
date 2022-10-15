package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorLoginTest {

    private ControladorLogin controladorLogin;
    private ServicioPersona servicioPersona;
    private HttpServletRequest request;

    @Before
    public void init(){
        this.servicioPersona = mock(ServicioPersona.class);
        this.request = mock(HttpServletRequest.class);
        controladorLogin = new ControladorLogin(servicioPersona);
    }
    @Test
    public void queControladorDevuelvaModelAndViewConVistaLoginYModelo(){
        ModelAndView vista = controladorLogin.irALogin();
        //TODO: preguntar al profe si hace falta castear datosLogin
        assertThat((DatosLogin) vista.getModel().get("datosLogin")).isInstanceOf(DatosLogin.class);
        assertThat(vista.getViewName()).isEqualTo("login");
    }
    @Test(expected = Exception.class)
    public void dadoUnaPersonaExistenteQueSePuedaIniciarSesion(){

        DatosLogin datosLogin = new DatosLogin("", "");
        Persona personaEsperada = new Persona();
        personaEsperada.setId(1L);

        when(this.servicioPersona.obtenerPersona(datosLogin.getEmail(),datosLogin.getPassword())).thenReturn(personaEsperada);
        //when(this.request.getSession().setAttribute("ID",personaEsperada.getId())).thenReturn()
        //TODO: preguntar al profe como mockear el request
        ModelAndView vista = controladorLogin.validarLogin(datosLogin, request);

        //assertThat((Long) request.getSession().getAttribute("ID")).isEqualTo(1L);
        assertThat(vista.getViewName()).isEqualTo("home");
    }
    @Test
    public void dadoUnaPersonaNulaQueRedirijaAHome(){

        DatosLogin datosLogin = new DatosLogin("", "");
        Persona personaEsperada = new Persona();
        personaEsperada.setId(1L);

        //TODO: preguntar al profe si hace falta mockear nulos
        when(this.servicioPersona.obtenerPersona(datosLogin.getEmail(),datosLogin.getPassword())).thenReturn(null);

        ModelAndView vista = controladorLogin.validarLogin(datosLogin, request);

        assertThat(vista.getViewName()).isEqualTo("login");
    }
}
