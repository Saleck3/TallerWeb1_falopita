package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCalendario {



    @RequestMapping(path = "/calendario")
    public ModelAndView irAAviso() {

        return new ModelAndView("calendario");
    }
}
