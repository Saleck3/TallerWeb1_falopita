package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.ValorRecomendado;

public class Suenio {

    public Suenio() {
    }

    public ValorRecomendado obtenerCantidadHorasSuenio(Integer edad) throws Exception {

        ValorRecomendado recomendacion = new ValorRecomendado();

        if (edad < 0) {
            throw new Exception("Una persona no puede tener edad negativa");
        }

        if (edad == 0) {
            recomendacion.setMinMax(12d, 16d);
        } else if (edad <= 2) {
            recomendacion.setMinMax(11d, 14d);
        } else if (edad <= 5) {
            recomendacion.setMinMax(10d, 13d);
        } else if (edad <= 12) {
            recomendacion.setMinMax(9d, 12d);
        } else if (edad <= 18) {
            recomendacion.setMinMax(8d, 10d);
        } else {
            //Adulto
            recomendacion.setMinMax(7d, 7d);
        }

        return recomendacion;
    }
}
