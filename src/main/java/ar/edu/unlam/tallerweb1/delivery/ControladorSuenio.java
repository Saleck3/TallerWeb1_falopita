package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.ValorRecomendado;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorSuenio {

    private ServicioPersona servicioPersona;
    private ServicioSuenio servicioSuenio;

    public ControladorSuenio() {
    }

    @Autowired
    public ControladorSuenio(ServicioPersona servicioPersona, ServicioSuenio servicioSuenio) {
        this.servicioPersona = servicioPersona;
        this.servicioSuenio = servicioSuenio;
    }

    @RequestMapping(path = "/suenio", method = RequestMethod.GET)
    public ModelAndView suenioGet(HttpServletRequest request) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        ModelMap modelo = new ModelMap();
        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }

        Persona persona = servicioPersona.obtenerPersona(idPersona);

        try {
            ValorRecomendado recomendacion = servicioSuenio.obtenerCantidadHorasSuenio(persona);
            modelo.put("recomendacion", recomendacion);
        } catch (Exception e) {
            modelo.put("errorEdad", e.getMessage());
        }

        modelo.put("registroNuevo",new RegistroSuenio());

        return new ModelAndView("suenio", modelo);
    }

}
