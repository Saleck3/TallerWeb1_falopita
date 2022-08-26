package ar.edu.unlam.tallerweb1.delivery;

import java.util.List;

public class CalculadoraDeCadena {

    public Long sumar(String cadena){

        if(cadena == "")
            throw new RuntimeException();

        List<String> numeros = List.of(cadena.split(","));

        Long resultado = Long.parseLong(numeros.get(0)) + Long.parseLong(numeros.get(1));
        return resultado;
    }
}
