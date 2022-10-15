package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistro {

    private ServicioPersona servicioPersona;

    @Autowired
    public ControladorRegistro(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    //cargar pagina de registro (para ingresar solo usr y pass)
    @RequestMapping(path = "/registrar-usuario")
    public ModelAndView irARegistro() {
        return irARegistro("");
    }

    public ModelAndView irARegistro(String mensaje) {
        ModelMap mapa = new ModelMap();
        mapa.put("persona", new Persona());
        mapa.put("mensaje", mensaje);
        return new ModelAndView("registrar-usuario", mapa);
    }


    // generar usuario en BD
    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView registrarUsuario(@ModelAttribute("persona") Persona personaIngresada, HttpServletRequest request) {

        if (servicioPersona.validarPersona(personaIngresada)) {
            servicioPersona.guardarPersona(personaIngresada);
            request.getSession().setAttribute("ID", personaIngresada.getId());
            return new ModelAndView("redirect:/perfil");
        }

        //Volver a la pagina de registro con un error
        return irARegistro("<span style=\"background-color:red; color:white;\">Hubo un error, por favor intente de nuevo</span><br>");
    }


}
