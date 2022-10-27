package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.ValorRecomendado;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.EdadNegativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class ControladorSuenio {

    private ServicioPersona servicioPersona;
    private ServicioSuenio servicioSuenio;
    ModelMap modelo = new ModelMap();

    @Autowired
    public ControladorSuenio(ServicioPersona servicioPersona, ServicioSuenio servicioSuenio) {
        this.servicioPersona = servicioPersona;
        this.servicioSuenio = servicioSuenio;
    }

    @RequestMapping(path = "/suenio", method = RequestMethod.GET)
    public ModelAndView suenioGet(HttpServletRequest request) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }

        Persona persona = servicioPersona.obtenerPersona(idPersona);

        try {
            ValorRecomendado recomendacion = servicioSuenio.obtenerCantidadHorasSuenio(persona);
            modelo.put("recomendacion", recomendacion);
            modelo.put("errorRecomendacion", recomendacion.getMensaje());
        } catch (EdadNegativaException e) {
            modelo.put("errorEdad", e.getMessage());
        }

        if (servicioSuenio.obtenerRegistrosSuenio(persona) != null) {
            modelo.put("registros", servicioSuenio.obtenerRegistrosSuenio(persona));
        }
        RegistroSuenio nuevo = new RegistroSuenio();
        nuevo.setHoraInicio(LocalDateTime.now());
        nuevo.setHoraFin(LocalDateTime.now());
        modelo.put("registroNuevo", nuevo);

        return new ModelAndView("suenio", modelo);
    }

    @RequestMapping(path = "/suenio/nuevoRegistro", method = RequestMethod.POST)
    public ModelAndView nuevoRegistro(HttpServletRequest request,
                                      //Prefiero manejarlos como fecha para poder mandarles el formato
                                      @RequestParam("horaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                      LocalDateTime horaInicio,
                                      @RequestParam("horaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                      LocalDateTime horaFin) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }
        Persona persona = servicioPersona.obtenerPersona(idPersona);
        RegistroSuenio nuevoRegistro = new RegistroSuenio(persona, horaInicio, horaFin);

        nuevoRegistro.setPersona(persona);
        servicioSuenio.guardarRegistroSuenio(nuevoRegistro);

        return new ModelAndView("redirect:/suenio");
    }

    @RequestMapping(path = "/suenio/eliminarRegistro", method = RequestMethod.GET)
    public ModelAndView eliminarRegistro(HttpServletRequest request, Long idRegistro) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            RegistroSuenio registro = servicioSuenio.obtenerRegistroSuenio(idRegistro);

            if (idPersona.equals(registro.getPersona().getId())) {
                servicioSuenio.eliminarRegistroSuenio(registro);
            }
        }

        return new ModelAndView("redirect:/suenio");
    }

    public String metricaDeHorasEnLaUltimaSemana(){
        servicioSuenio.cantidadHorasDormidaEnLosUltimosXDias();
    }
}
