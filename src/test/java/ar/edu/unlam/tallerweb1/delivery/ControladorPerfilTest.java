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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

public class ControladorPerfilTest extends SpringTest {

    private ControladorPerfil controladorPerfilParaTest;
    @Mock
    ServicioPersona servicioPersonaMock;
    @Mock
    HttpServletRequest requestMock;
    @Mock
    HttpSession sessionMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controladorPerfilParaTest = new ControladorPerfil(servicioPersonaMock);

        when(requestMock.getSession()).thenReturn(sessionMock);
    }

    @Test
    public void dadoQueNoEstaLogueadoDevolverAlLoginConError() {
        dadoQueTengoUnIdEnLaSesion(null);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(null, requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("login");
        assertThat((String) mavDevuelto.getModel().get("error")).isNotNull();
    }

    @Test
    public void dadoQueEstaLogueadoDevolverPerfilConDatos() {
        Persona personaMock = dadoQueTengoUnaPersonaCualquiera(true, 1L);
        dadoQueTengoUnIdEnLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(personaMock);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(null, requestMock);
        
        assertThat(mavDevuelto.getViewName()).isEqualTo("perfil");
        assertThat(mavDevuelto.getModel().get("persona")).isNotNull();
    }

    @Test //TODO: incompleto
    public void dadoQueLleganErroresDevolverMavConErrores(){
        Persona personaMock = dadoQueTengoUnaPersonaCualquiera(true, 1L);
        List<String> errores = dadoQueTengoUnaListaConErrores();
        dadoQueTengoUnIdEnLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(personaMock);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(errores, requestMock);

        assertThat((List<String>) mavDevuelto.getModel().get("errores")).hasSize(3);
    }

    private List<String> dadoQueTengoUnaListaConErrores() {
        List<String> errores = new ArrayList<String>();
        errores.add("error 1");
        errores.add("error 2");
        errores.add("error 3");
        return errores;
    }

    @Test //TODO: incompleto
    public void dadoQueLosDatosNoSonValidosDevolverErrorEnParametro(){
        //dadoQueLlegaUnaPersonaConDatosNoValidos
        Persona personaInvalida = dadoQueTengoUnaPersonaCualquiera(false, 1L);
        dadoQueTengoUnIdEnLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(personaInvalida);

        ModelAndView mavDevuelto = controladorPerfilParaTest.modificarPerfil(personaInvalida, requestMock);
        //devuelvoUrlConParametro
        assertThat(mavDevuelto.getViewName()).isEqualTo("redirect:/perfil?errores=1");
    }

    @Test
    public void dadoQueLosDatosSiSonValidosDevolverAVistaPerfil(){
        //dadoQueLlegaUnaPersonaConDatosValidos
        Persona personaValida = dadoQueTengoUnaPersonaCualquiera(true, 1L);
        dadoQueTengoUnIdEnLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(personaValida);

        ModelAndView mavDevuelto = controladorPerfilParaTest.modificarPerfil(personaValida, requestMock);
        //devuelvoUrlConParametro
        assertThat(mavDevuelto.getViewName()).isEqualTo("redirect:/perfil");
    }

    private Persona dadoQueTengoUnaPersonaCualquiera(Boolean valida, Long id){
        Persona p = new Persona("", "a", "a", 1, 1.0, null, 'a');
        p.setId(id);

        if(valida){
            p.setEmail("a");
        }

        return p;
    }

    private void dadoQueTengoUnIdEnLaSesion(Long id){
        when(sessionMock.getAttribute("ID")).thenReturn(id);
    }

    private void dadoQueObtengoUnaPersonaDelServicioPersona(Persona p){
        when(servicioPersonaMock.obtenerPersona(anyLong())).thenReturn(p);
    }
}
