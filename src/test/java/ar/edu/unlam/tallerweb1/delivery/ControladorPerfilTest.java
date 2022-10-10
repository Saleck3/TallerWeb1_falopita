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

import java.util.HashMap;
import java.util.Map;

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
    public void dadoQueNoEstaLogueadoDevolverAVistaLoginConError() {
        dadoQueObtengoUnIdDeLaSesion(null);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("login");
        assertThat((String) mavDevuelto.getModel().get("error")).isNotNull();
    }

    @Test
    public void dadoQueEstaLogueadoDevolverAVistaPerfil() {
        dadoQueObtengoUnIdDeLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(true, 1L);

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(requestMock);
        
        assertThat(mavDevuelto.getViewName()).isEqualTo("perfil");
        assertThat(mavDevuelto.getModel().get("persona")).isNotNull();
    }

    @Test
    public void dadoQueLleganErroresDevolverMavConErrores(){
        dadoQueObtengoUnIdDeLaSesion(1L);
        dadoQueObtengoUnaPersonaDelServicioPersona(false, 1L);
        dadoQueObtengoUnMapaConErroresDeLaSesion();

        ModelAndView mavDevuelto = controladorPerfilParaTest.irAPerfil(requestMock);

        assertThat((Map<String,String>) mavDevuelto.getModel().get("errores")).hasSize(3);
    }

    @Test
    public void dadoQueNoLleganErroresDevolverAVistaPerfil(){
        dadoQueObtengoUnIdDeLaSesion(1L);
        Persona personaValida = dadoQueTengoUnaPersonaCualquiera(true, 1L);

        ModelAndView mavDevuelto = controladorPerfilParaTest.modificarPerfil(personaValida, requestMock);

        assertThat(mavDevuelto.getViewName()).isEqualTo("redirect:/perfil");
    }

    private Persona dadoQueTengoUnaPersonaCualquiera(Boolean valida, Long id){
        Persona p = new Persona("", "1234", "Persona", 18, 50.0, 1.70, 'M');
        p.setId(id);

        if(valida){
            p.setEmail("persona@example.com");
        }

        return p;
    }

    private void dadoQueObtengoUnIdDeLaSesion(Long id){
        when(sessionMock.getAttribute("ID")).thenReturn(id);
    }

    //una persona valida es aquella que tiene todos sus campos llenos y no vacios
    //crea una instancia de persona con atributo email como cadena vacia o con una cadena valida
    private void dadoQueObtengoUnaPersonaDelServicioPersona(Boolean valida, Long id){
        Persona p = dadoQueTengoUnaPersonaCualquiera(valida, id);
        when(servicioPersonaMock.obtenerPersona(anyLong())).thenReturn(p);
    }

    private void dadoQueObtengoUnMapaConErroresDeLaSesion() {
        Map<String,String> mapaMock = new HashMap<>();
        mapaMock.put("1","1");
        mapaMock.put("2","2");
        mapaMock.put("3","3");
        when(sessionMock.getAttribute("errores")).thenReturn(mapaMock);
    }
}
