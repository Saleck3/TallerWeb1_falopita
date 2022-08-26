package ar.edu.unlam.tallerweb1.delivery;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CalculadoraDeCadenaTest {

    @Test(expected = RuntimeException.class)
    public void DebeLanzarUnaExceptionSiRecibeUnaCadenaVacia(){

        //Preparación
        CalculadoraDeCadena calculadoraDeCadena = DadoQueTenemosUnaCalculadora();

        //Ejecución
        Long resultado = CuandoRealizamosLaOperacionSumar(calculadoraDeCadena, "");

        //Validación
    }

    @Test
    public void DebeDevolver4SiRecibe2Y2(){
        //Preparación
        CalculadoraDeCadena calculadoraDeCadena = DadoQueTenemosUnaCalculadora();

        //Ejecución
        Long resultado = CuandoRealizamosLaOperacionSumar(calculadoraDeCadena, "2,2");

        //Validación
        EntoncesReciboElResultado(resultado, 4L);
    }

    @Test
    public void DebeDevolver6SiRecibe3Y3(){
        //Preparación
        CalculadoraDeCadena calculadoraDeCadena = DadoQueTenemosUnaCalculadora();

        //Ejecución
        Long resultado = CuandoRealizamosLaOperacionSumar(calculadoraDeCadena, "3,3");

        //Validación
        EntoncesReciboElResultado(resultado, 6L);
    }

    private void EntoncesReciboElResultado(Long resultadoObtenido, Long resultadoEsperado) {
        assertThat(resultadoObtenido).isEqualTo(resultadoEsperado);
    }

    private Long CuandoRealizamosLaOperacionSumar(CalculadoraDeCadena calculadoraDeCadena, String cadena){
        return calculadoraDeCadena.sumar(cadena);
    }

    private CalculadoraDeCadena DadoQueTenemosUnaCalculadora(){
        return new CalculadoraDeCadena();
    }
}
