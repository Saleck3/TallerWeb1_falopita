package ar.edu.unlam.tallerweb1.delivery;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorAlmacenTest {

    @Test
    public void DebeDevolverUnaListaVaciaSinoTieneProductos(){

        ControladorAlmacen controladorAlmacen = DadoQueTenemosUnControladorAlmacenSinProductos();
        List<String> productos = CuandoPidoLaListaDeProductos(controladorAlmacen);
        EntoncesTengoUnaListaVacia(productos);
    }

    @Test
    public void DebeUsarLaVistaProductosAlConsultarLosProductosYEstosExistan(){
        ControladorAlmacen controladorAlmacen = DadoQueTenemosUnControladorAlmacenConProductos();
        String nombreVistaRecibida = CuandoObtengoLaVistaDeLaListaDeProductos(controladorAlmacen);
        EntoncesObtengoLaVistaProductos(nombreVistaRecibida);
    }

    @Test
    public void DebeUsarLaVistaErrorAlConsultarLosProductosYNohayan(){
        ControladorAlmacen controladorAlmacen = DadoQueTenemosUnControladorAlmacenSinProductos();
        String nombreVistaRecibida = CuandoObtengoLaVistaDeLaListaDeProductos(controladorAlmacen);
        EntoncesObtengoLaVistaError(nombreVistaRecibida);
    }

    private void EntoncesObtengoLaVistaError(String nombreVistaRecibida) {
        assertThat(nombreVistaRecibida).isEqualTo("error");
    }

    private void EntoncesObtengoLaVistaProductos(String nombreVistaRecibida) {
        assertThat(nombreVistaRecibida).isEqualTo("productos");
    }

    private String CuandoObtengoLaVistaDeLaListaDeProductos(ControladorAlmacen controladorAlmacen) {
        ModelAndView mavRecibido = controladorAlmacen.listaProductos();
        return mavRecibido.getViewName();
    }

    private void EntoncesTengoUnaListaVacia(List<String> productos) {
        assertThat(productos).hasSize(0);
    }

    private List<String> CuandoPidoLaListaDeProductos(ControladorAlmacen controladorAlmacen) {
        ModelAndView mavRecibido = controladorAlmacen.listaProductos();
        return (List<String>) mavRecibido.getModel().get("productos");
    }

    private ControladorAlmacen DadoQueTenemosUnControladorAlmacenSinProductos() {
        return new ControladorAlmacen(new ArrayList<>());
    }

    private ControladorAlmacen DadoQueTenemosUnControladorAlmacenConProductos(){
        List<String> productos = new ArrayList<>();
        productos.add("Peras");

        return new ControladorAlmacen(productos);
    }
}
