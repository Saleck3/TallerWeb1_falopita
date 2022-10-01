package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.ServicioSuenioImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SuenioTest extends SpringTest {

    @Test
    public void queDevuelvaUnTiempoCorrectoSegunEdad() {
        ServicioSuenioImpl suenio = new ServicioSuenioImpl();

        try {
            assertThat(suenio.obtenerCantidadHorasSuenio(7).getMinimo()).isEqualTo(9d);
            assertThat(suenio.obtenerCantidadHorasSuenio(7).getMaximo()).isEqualTo(12d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = Exception.class)
    public void siTengoEdadNegativaTiroExcepcion() throws Exception {
        ServicioSuenioImpl suenio = new ServicioSuenioImpl();
        suenio.obtenerCantidadHorasSuenio(-1);
    }

    @Test
    public void queDevuelvaUnTiempoCorrectoSegunPersona() {

        Persona persona = new Persona("tuvieja@example.com","poyas123","tuvieja", 5, 25d, 170.0,'m');
        ServicioSuenioImpl suenio = new ServicioSuenioImpl();

        try {
            assertThat(suenio.obtenerCantidadHorasSuenio(persona).getMinimo()).isEqualTo(10d);
            assertThat(suenio.obtenerCantidadHorasSuenio(persona).getMaximo()).isEqualTo(13d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
