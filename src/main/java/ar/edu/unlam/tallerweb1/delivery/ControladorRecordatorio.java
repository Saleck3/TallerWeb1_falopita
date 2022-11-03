package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio.*;
import ar.edu.unlam.tallerweb1.domain.recordatorio.ServicioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        modelo.put("recordatorios", servicioRecordatorio.listarTodos(idPersona));

        return new ModelAndView("recordatorios", modelo);
    }

    @RequestMapping(path = "/recordatorios/formCrear", method = RequestMethod.GET)
    public ModelAndView formRecordatorio(@RequestParam("opc") Integer tipo){
        ModelMap modelo = new ModelMap();

        DatosRecordatorio datos = new DatosRecordatorio();
        datos.setTipo(tipo);

        modelo.put("datosRecordatorio", datos);

        //TODO: cambiar esto por una forma de validar con enum; hardcoded
        if(datos.getTipoEnum() == TipoRecordatorio.UNICO){
            return new ModelAndView("formRecordatorioUnico", modelo);
        }
        else{
            return new ModelAndView("formRecordatorioRecurrente", modelo);
        }
    }

    @RequestMapping(path = "/recordatorios/crear", method = RequestMethod.POST)
    public ModelAndView crearRecordatorio(@ModelAttribute DatosRecordatorio datos, HttpServletRequest request){
        HttpSession sesion = request.getSession();
        Long idPersona = (Long) sesion.getAttribute("ID");

        servicioRecordatorio.crearRecordatorio(datos, idPersona);

        return new ModelAndView("redirect:/recordatorios");
    }

    @RequestMapping(path = "/recordatorios/notificarRecordatorio", method = RequestMethod.GET)
    public void actualizarEstadoANotificado(HttpServletRequest request){
        HttpSession sesion = request.getSession(false);

        if(sesion != null){
            servicioRecordatorio.actualizarEstado();
        }
    }

    /*
    @RequestMapping(path = "/recordatorios/obtenerRecordatorio", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Recordatorio obtenerRecordatorio(){
        Persona p = new Persona();
        p.setId(1L);
        p.setNombre("Ejemplo");
        Recordatorio r = new Recordatorio();
        r.setPersona(p);
        r.setContenido("Contenido ejemplo");
        List<FechaRecordatorio> l = new ArrayList<>();
        l.add(new FechaRecordatorio(LocalDate.of(2022, 10, 11), LocalTime.of(12,0,0)));
        l.add(new FechaRecordatorio(LocalDate.of(2022, 10, 12), LocalTime.of(12,0,0)));
        l.add(new FechaRecordatorio(LocalDate.of(2022, 10, 13), LocalTime.of(12,0,0)));
        l.add(new FechaRecordatorio(LocalDate.of(2022, 10, 14), LocalTime.of(12,0,0)));
        r.setFechas(l);

        return r;
    }
    */
}
