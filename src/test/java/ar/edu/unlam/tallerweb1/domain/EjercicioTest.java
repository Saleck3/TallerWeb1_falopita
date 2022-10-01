package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.ejercicio.Ejercicio;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class EjercicioTest extends SpringTest{

    @Test
    public  void queDevuelvaUnaDistanciaCorrectaSegunPersona(){

        Persona persona = new Persona ("tuvieja@example.com","poyas123","Alejandro", 25, 90d, 170.0, 'm');
        Ejercicio ejercicio = new Ejercicio();

        try {
            assertThat(ejercicio.obtenerKilometrosSegunPeso(persona).getMinimo()).isEqualTo(2d);
            assertThat(ejercicio.obtenerKilometrosSegunPeso(persona).getMaximo()).isEqualTo(4d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = Exception.class)
    public void siTengoPesoNegativoTiroExcepcion() throws Exception{
        Persona persona = new Persona ("tuvieja@example.com","poyas123","Alejandro", 25, -90d, 170.0, 'm');
        Ejercicio ejercicio = new Ejercicio();

        ejercicio.obtenerKilometrosSegunPeso(persona);
    }

    @Test(expected = Exception.class)
    public void siTengoAlturaNegativaTiroExcepcion() throws Exception{
        Persona persona = new Persona ("tuvieja@example.com","poyas123","Alejandro", 25, 90d, -170.0, 'm');
        Ejercicio ejercicio = new Ejercicio();

        ejercicio.obtenerKilometrosSegunPeso(persona);
    }

}
