package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.evento.Evento;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorCalendario {

    private ServicioPersona servicioPersona;
    ModelMap model = new ModelMap();

    @Autowired
    public ControladorCalendario(ServicioPersona servicioPersona) {
        this.servicioPersona = servicioPersona;
    }

    @RequestMapping(path = "/calendario")
    public ModelAndView irACalendario(HttpServletRequest request) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            model.put("datosLogin", new DatosLogin());
            model.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", model);
        }


        Evento evento = new Evento();
        evento.setPersona(servicioPersona.obtenerPersona(idPersona));
        evento.setDate("January/1/2022");
        evento.setDescription("probandoputas");
        evento.setEveryYear(true);
        evento.setName("nombre");

        Evento evento1 = new Evento();
        evento1.setPersona(servicioPersona.obtenerPersona(idPersona));
        evento1.setDate("January/2/2022");
        evento1.setDescription("probandoputas");
        evento1.setEveryYear(true);
        evento1.setName("nombre1");

        Evento evento2 = new Evento();
        evento2.setPersona(servicioPersona.obtenerPersona(idPersona));
        evento2.setDate("January/3/2022");
        evento2.setDescription("probandoputas");
        evento2.setEveryYear(true);
        evento2.setName("nombre2");

        List<Evento> listaDeEvento = new ArrayList<>();

        listaDeEvento.add(evento);
        listaDeEvento.add(evento1);
        listaDeEvento.add(evento2);

        Util.ponerErrores(model, sesion);


        Gson Json = new Gson();
        String eventosJson = Json.toJson(listaDeEvento);
        model.put("persona", servicioPersona.obtenerPersona(idPersona));
        model.put("eventos", eventosJson);
        return new ModelAndView("calendario", model);
    }

}
