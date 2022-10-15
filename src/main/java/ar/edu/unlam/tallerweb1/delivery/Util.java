package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class Util {

    public static void ponerErrores(ModelMap modelo, HttpSession sesion){
        Map<String, Object> mapaErrores = (Map<String, Object>) sesion.getAttribute("errores");

        if(mapaErrores != null){
            modelo.putAll(mapaErrores);
            sesion.removeAttribute("errores");
        }
    }
}
