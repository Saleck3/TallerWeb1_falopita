package ar.edu.unlam.tallerweb1.domain.ejercicio;

import ar.edu.unlam.tallerweb1.domain.ValorRecomendado;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public class Ejercicio {

    public Ejercicio(){
    }

    public ValorRecomendado obtenerKilometrosSegunPeso(Persona persona) throws Exception {

        ValorRecomendado recomendacion = new ValorRecomendado();

        if(persona.getPeso() < 0 || persona.getAltura() < 0) {
            throw new Exception("Una persona no puede tener peso y altura negativa");
        }

        if (persona.getPeso() > 100) {
            recomendacion.setMinMax(2.0, 4.0);
        } else if (persona.getPeso() > 80 && persona.getAltura() > 170.0){
            recomendacion.setMinMax(1, 3);
        } else if (persona.getPeso() > 80){
            recomendacion.setMinMax(2, 4);
        } else {
            recomendacion.setMinMax(1, 2);
        }

        return recomendacion;
    }
}
