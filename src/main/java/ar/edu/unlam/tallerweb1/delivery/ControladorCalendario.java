package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorCalendario {

    private ServicioPersona servicioPersona;
    ModelMap model = new ModelMap();

    @Autowired
    public ControladorCalendario(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    @RequestMapping(path = "/calendario")
    public ModelAndView irAAviso(HttpServletRequest request) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long)sesion.getAttribute("ID");

        if (idPersona == null) {
            model.put("datosLogin", new DatosLogin());
            model.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", model);
        }

        Util.ponerErrores(model, sesion);

        model.put("persona", servicioPersona.obtenerPersona(idPersona));

        return new ModelAndView("calendario", model);
    }

}
