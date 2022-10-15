package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.nota.ServicioNota;
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
import java.util.List;

@Controller
public class ControladorNotas {

    private ServicioNota servicioNota;
    private ServicioPersona servicioPersona;
    ModelMap modelo = new ModelMap();

    @Autowired
    public ControladorNotas(ServicioPersona servicioPersona, ServicioNota servicioNota) {
        this.servicioPersona = servicioPersona;
        this.servicioNota = servicioNota;
    }

    //vista de todas las notas
    @RequestMapping(path = "/notas")
    public ModelAndView verNotas(HttpServletRequest request, boolean archivadas) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }

        Persona persona = servicioPersona.obtenerPersona(idPersona);


        List<Nota> notas = archivadas ? servicioNota.listarArchivadas(persona) : servicioNota.listar(persona);
        modelo.put("notas", notas);
        modelo.put("nuevaNota", new Nota());
        return new ModelAndView("notas", modelo);
    }


    @RequestMapping(path = "/notas/nuevaNota", method = RequestMethod.POST)
    public ModelAndView nuevaNota(HttpServletRequest request, @ModelAttribute Nota nota) {

        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        if (idPersona == null) {
            modelo.put("datosLogin", new DatosLogin());
            modelo.put("error", "Debe iniciar sesion para usar la aplicación");
            return new ModelAndView("login", modelo);
        }
        Persona persona = servicioPersona.obtenerPersona(idPersona);
        nota.setPersona(persona);
        nota.activar();

        servicioNota.guardar(nota);

        return new ModelAndView("redirect:/notas");
    }

    //Metodo anclaje
    @RequestMapping(path = "/anclarNota")
    public ModelAndView anclarNota(HttpServletRequest request, Long idNota) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            Nota notaAAnclar = servicioNota.obtener(idNota);
            //Le pertenece la nota
            if (idPersona.equals(notaAAnclar.getPersona().getId())) {
                servicioNota.anclar(notaAAnclar);
            }
        }

        return new ModelAndView("redirect:/notas");
    }


    @RequestMapping(path = "/desanclarNota")
    public ModelAndView desanclarNota(HttpServletRequest request, Long idNota) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            Nota notaADesanclar = servicioNota.obtener(idNota);
            //Le pertenece la nota
            if (idPersona.equals(notaADesanclar.getPersona().getId())) {
                servicioNota.desanclar(notaADesanclar);
            }
        }

        return new ModelAndView("redirect:/notas");
    }

    @RequestMapping(path = "/archivarNota")
    public ModelAndView archivarNota(HttpServletRequest request, Long idNota) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            Nota notaAArchivar = servicioNota.obtener(idNota);
            //Le pertenece la nota
            if (idPersona.equals(notaAArchivar.getPersona().getId())) {
                servicioNota.archivar(notaAArchivar);
            }
        }

        return new ModelAndView("redirect:/notas");
    }

    @RequestMapping(path = "/desarchivarNota")
    public ModelAndView desarchivarNota(HttpServletRequest request, Long idNota) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            Nota notaADesarchivar = servicioNota.obtener(idNota);
            //Le pertenece la nota
            if (idPersona.equals(notaADesarchivar.getPersona().getId())) {
                servicioNota.desarchivar(notaADesarchivar);
            }
        }

        return new ModelAndView("redirect:/notas");
    }

    @RequestMapping(path = "/eliminarNota")
    public ModelAndView eliminarNota(HttpServletRequest request, Long idNota) {
        HttpSession sesion = request.getSession();

        Long idPersona = (Long) sesion.getAttribute("ID");

        //Esta logueado
        if (idPersona != null) {
            Nota notaAEliminar = servicioNota.obtener(idNota);
            //Le pertenece la nota
            if (idPersona.equals(notaAEliminar.getPersona().getId())) {
                servicioNota.eliminar(notaAEliminar);
            }
        }

        return new ModelAndView("redirect:/notas");
    }
}
