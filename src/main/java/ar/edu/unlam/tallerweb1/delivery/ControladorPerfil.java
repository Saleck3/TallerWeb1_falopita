package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPerfil {

    private ServicioPersona servicioPersona;

    @Autowired
    public ControladorPerfil(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    @RequestMapping(path = "/perfil")
    public ModelAndView irAPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idPersona = (Long) request.getSession().getAttribute("ID");

        if (idPersona == null) {
            model.put("datosLogin", new DatosLogin());
            model.put("error", "Debe loguerse para usar la aplicación");
            return new ModelAndView("login", model);
        }

        Persona persona = servicioPersona.obtenerPersona(idPersona);
        DatosPerfil datosPerfil = new DatosPerfil(persona);
        model.put("datosPerfil", datosPerfil);

        return new ModelAndView("perfil", model);
    }
}
