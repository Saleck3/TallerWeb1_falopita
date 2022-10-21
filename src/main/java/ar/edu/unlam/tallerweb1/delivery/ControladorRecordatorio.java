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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorRecordatorio {

    private ServicioPersona servicioPersona;
    private ServicioRecordatorio servicioRecordatorio;

    @Autowired
    public ControladorRecordatorio(ServicioPersona servicioPersona, ServicioRecordatorio servicioRecordatorio) {
        this.servicioPersona = servicioPersona;
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

        Persona personaObtenida = servicioPersona.obtenerPersona(idPersona);

        List<Recordatorio> recordatorios = servicioRecordatorio.listarRecordatorios(personaObtenida);

        modelo.put("recordatorios", recordatorios);

        return new ModelAndView("recordatorios", modelo);
    }

    @RequestMapping(path = "/recordatorios/crear")
    public ModelAndView crearRecordatorio(@ModelAttribute DatosRecordatorio recordatorio, HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");
        Persona personaObtenida = servicioPersona.obtenerPersona(idPersona);
        Recordatorio recordatorioGuardado = servicioRecordatorio.crearRecordatorio(recordatorio, personaObtenida);

        return new ModelAndView("recordatorios", modelo);
    }
}
