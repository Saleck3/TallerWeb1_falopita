package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class ControladorAlmacen {

    private List<String> productos;

    public ControladorAlmacen(List<String> productos){
        this.productos = productos;
    }

    public ModelAndView listaProductos() {

        ModelMap modelo = new ModelMap();
        modelo.put("productos", this.productos);

        if(this.productos.size() > 0)
            return new ModelAndView("productos", modelo);

        return new ModelAndView("error", modelo);
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}