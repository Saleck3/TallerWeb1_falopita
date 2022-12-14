package ar.edu.unlam.tallerweb1.delivery;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorRegistroTest {

    private ControladorRegistro controladorRegistro;
    @Mock
    ServicioPersona servicioPersona;
    @Mock
    HttpServletRequest requestMock;
    @Mock
    HttpSession session;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controladorRegistro = new ControladorRegistro(servicioPersona);

        when(requestMock.getSession()).thenReturn(session);
    }

    @Test
    public void queCuandoQuieraRegistrarmeMeLleveAlRegistroYEspereUnUsuario() {
        ModelAndView vista = controladorRegistro.irARegistro();
        assertThat(vista.getModel().get("persona")).isInstanceOf(Persona.class);
        assertThat(vista.getViewName()).isEqualTo("registrar-usuario");
    }

    @Test
    public void queCuandoRegistroAUnUsuarioValidoMeRedirijaADatosPersonales() {

        when(requestMock.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(null);
        Persona personaValida = dadaUnaPersonaConDatosValidos();
        ModelAndView vistaDevuelta = cuandoMeRegistroYEsValida(personaValida);
        //TODO Cambiar por pagina de datos personales
        assertThat(vistaDevuelta.getViewName()).isEqualTo("redirect:/perfil");
    }

    @Test
    public void queCuandoRegistroAUnUsuarioNOValidoMeRedirijaDeVueltaARegistro() {
        Persona personaInvalida = dadaUnaPersonaConDatosInvalidos();
        ModelAndView vistaDevuelta = cuandoMeRegistroYNOEsValido(personaInvalida);
        assertThat(vistaDevuelta.getViewName()).isEqualTo("registrar-usuario");
    }


    private Persona dadaUnaPersonaConDatosInvalidos() {
        return new Persona("", "", "", 200, 560.4, 370.70, 'a');

    }

    private Persona dadaUnaPersonaConDatosValidos() {
        return new Persona("persona@example.com", "1234567894", "Nombre 5", 23, 60.4, 170.70, 'm');
    }

    private ModelAndView cuandoMeRegistroYEsValida(Persona personaValida) {
        //mock validar persona
        when(this.servicioPersona.validarPersona(personaValida)).thenReturn(true);
        return controladorRegistro.registrarUsuario(personaValida, requestMock);
    }

    private ModelAndView cuandoMeRegistroYNOEsValido(Persona personaInvalida) {
        when(this.servicioPersona.validarPersona(personaInvalida)).thenReturn(false);
        return controladorRegistro.registrarUsuario(personaInvalida, requestMock);
    }
}
