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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ControladorPerfil {

    private ServicioPersona servicioPersona;

    @Autowired
    public ControladorPerfil(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    @RequestMapping(path = "/perfil")
    public ModelAndView irAPerfil(HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }

        Util.ponerErrores(modelo, sesion);

        modelo.put("persona", servicioPersona.obtenerPersona(idPersona));

        return new ModelAndView("perfil", modelo);
    }

    @RequestMapping(path = "/perfil/modificar", method = RequestMethod.POST)
    public ModelAndView modificarPerfil(@ModelAttribute Persona personaAModificar, HttpServletRequest request){
        HttpSession sesion = request.getSession();
        Map<String, Object> mapaErrores = personaValida(personaAModificar);

        if(!mapaErrores.isEmpty()){
            sesion.setAttribute("errores", mapaErrores);
            return new ModelAndView("redirect:/perfil");
        }

        personaAModificar.setId((Long) sesion.getAttribute("ID"));
        servicioPersona.modificarPersona(personaAModificar);
        return new ModelAndView("redirect:/perfil");
    }
    //TODO: Agregar más validaciones
    private HashMap<String, Object> personaValida(Persona personaAValidar){

        HashMap<String, Object> errores = new HashMap<>();

        if(personaAValidar.getNombre() == null || personaAValidar.getNombre().equals("")){
            errores.put("errorNombre", "error en el nombre");
        };

        if(personaAValidar.getEmail() == null || personaAValidar.getEmail().equals("")){
            errores.put("errorEmail", "error en el email");
        }

        if(personaAValidar.getPassword() == null || personaAValidar.getPassword().equals("")){
            errores.put("errorPass", "error en el password");
        }

        if(personaAValidar.getEdad() == null || personaAValidar.getEdad() <= 0){
            errores.put("errorEdad", "error en la edad");
        }

        if(personaAValidar.getAltura() == null || personaAValidar.getAltura() <= 0){
            errores.put("errorAltura", "error en la altura");
        }

        if(personaAValidar.getPeso() == null || personaAValidar.getPeso() <= 0) {
            errores.put("errorPeso", "error en el peso");
        }

        //if(personaAValidar.getSexo().equals("")) return false;
        return errores;
    }
}
