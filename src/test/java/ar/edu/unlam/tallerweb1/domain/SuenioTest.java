package ar.edu.unlam.tallerweb1.domain;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SuenioTest extends SpringTest {

    @Test
    public void queDevuelvaUnTiempoCorrectoSegunEdad() {
        Suenio suenio = new Suenio();

        try {
            assertThat(suenio.obtenerCantidadHorasSuenio(7).getMinimo()).isEqualTo(9d);
            assertThat(suenio.obtenerCantidadHorasSuenio(7).getMaximo()).isEqualTo(12d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = Exception.class)
    public void siTengoEdadNegativaTiroExcepcion() throws Exception {
        Suenio suenio = new Suenio();
        suenio.obtenerCantidadHorasSuenio(-1);
    }

    @Test
    public void queDevuelvaUnTiempoCorrectoSegunPersona() {

        Persona persona = new Persona("tuvieja", 5, 25d, "m");
        Suenio suenio = new Suenio();

        try {
            assertThat(suenio.obtenerCantidadHorasSuenio(persona).getMinimo()).isEqualTo(10d);
            assertThat(suenio.obtenerCantidadHorasSuenio(persona).getMaximo()).isEqualTo(13d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
