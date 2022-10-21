package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;
import ar.edu.unlam.tallerweb1.domain.recordatorio.ServicioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorRecordatorio {

    private ServicioRecordatorio servicioRecordatorio;

    @Autowired
    public ControladorRecordatorio(ServicioRecordatorio servicioRecordatorio) {
        this.servicioRecordatorio = servicioRecordatorio;
    }

    @RequestMapping(path = "/recordatorios", method = RequestMethod.GET)
    public ModelAndView irARecordatorios(HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }

        modelo.put("datosRecordatorio", new DatosRecordatorio());
        modelo.put("recordatorios", servicioRecordatorio.listarRecordatorios(idPersona));

        return new ModelAndView("recordatorios", modelo);
    }

    @RequestMapping(path = "/recordatorios/crear", method = RequestMethod.POST)
    public ModelAndView crearRecordatorio(@ModelAttribute DatosRecordatorio datos, HttpServletRequest request){
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");
        Recordatorio recordatorioGuardado = servicioRecordatorio.crearRecordatorio(datos, idPersona);

        return new ModelAndView("redirect:/recordatorios");
    }

    @RequestMapping(path = "/recordatorios/eliminar", method = RequestMethod.GET)
    public ModelAndView eliminarRecordatorio(@RequestParam Long id){

        //sospecho que esto no está bien, obtener el recordatorio cuando yo en realidad tengo la lista en memoria
        servicioRecordatorio.eliminarRecordatorio(id);
        return new ModelAndView("redirect:/recordatorios");
    }
}
