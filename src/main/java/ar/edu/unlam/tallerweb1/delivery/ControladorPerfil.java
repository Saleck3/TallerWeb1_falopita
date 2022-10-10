package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class ControladorPerfil {

    private ServicioPersona servicioPersona;

    @Autowired
    public ControladorPerfil(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    @RequestMapping(path = "/perfil")
    public ModelAndView irAPerfil(@RequestParam(required = false) List<String> errores, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idPersona = (Long) request.getSession().getAttribute("ID");

        if (idPersona == null) {
            model.put("datosLogin", new DatosLogin());
            model.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", model);
        }

        model.put("errores", errores); //TODO: incompleto
        model.put("persona", servicioPersona.obtenerPersona(idPersona));

        return new ModelAndView("perfil", model);
    }

    @RequestMapping(path = "/perfil/modificar", method = RequestMethod.POST)
    public ModelAndView modificarPerfil(@ModelAttribute Persona personaAModificar, HttpServletRequest request){
        personaAModificar.setId((Long) request.getSession().getAttribute("ID"));

        if(!personaValida(personaAModificar)){ //TODO: incompleto
            return new ModelAndView("redirect:/perfil?errores=1");
        }
        servicioPersona.modificarPersona(personaAModificar);

        return new ModelAndView("redirect:/perfil");
    }

    private Boolean personaValida(Persona personaAValidar){

        if(personaAValidar.getNombre().equals("") || personaAValidar.getNombre() == null) return false;

        if(personaAValidar.getEmail().equals("") || personaAValidar.getEmail() == null) return false;

        if(personaAValidar.getPassword().equals("") || personaAValidar.getPassword() == null) return false;

        if(personaAValidar.getEdad() <= 0 || personaAValidar.getEdad() == null) return false;

        if(personaAValidar.getAltura() <= 0 || personaAValidar.getAltura() == null) return false;

        if(personaAValidar.getPeso() <= 0 || personaAValidar.getPeso() == null) return false;

        //if(personaAValidar.getSexo().equals("")) return false; //TODO: incompleto

        return true;

    }
}
