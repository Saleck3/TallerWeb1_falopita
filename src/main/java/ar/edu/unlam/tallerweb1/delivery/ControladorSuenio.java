package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.suenio.DatosSuenio;
import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSuenio {

    @RequestMapping(path = "/suenio", method = RequestMethod.GET)
    public ModelAndView suenioGet() {
        ModelMap mapa = new ModelMap();
        mapa.put("edadObtenida", new DatosSuenio());
        return new ModelAndView("suenio", mapa);
    }

    @RequestMapping(path = "/suenio", method = RequestMethod.POST)
    public ModelAndView suenioPost(@ModelAttribute DatosSuenio datosSuenio) {
        ModelMap mapa = new ModelMap();
        mapa.put("edadObtenida", datosSuenio.getEdadObtenida());
        return new ModelAndView("horas", mapa);
    }
}
