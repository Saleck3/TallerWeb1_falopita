package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.springframework.stereotype.Service;

@Service
public class ServicioSuenioImpl implements ServicioSuenio {

    public ServicioSuenioImpl() {
    }

    public ValorRecomendado obtenerCantidadHorasSuenio(Integer edad) throws Exception {


        if (edad < 0) {
            throw new Exception("Una persona no puede tener edad negativa");
        }

        double minimo = 7d;
        double maximo = 7d;

        if (edad <= 2) {
            minimo = 11d;
            maximo = 14d;
        } else if (edad <= 5) {
            minimo = 10d;
            maximo = 13d;
        } else if (edad <= 12) {
            minimo = 9d;
            maximo = 12d;
        } else if (edad <= 18) {
            minimo = 8d;
            maximo = 10d;
        } else {
            //Adulto
            minimo = 7d;
            maximo = 8d;
        }

        return new ValorRecomendado(minimo, maximo);
    }

    public ValorRecomendado obtenerCantidadHorasSuenio(Persona persona) throws Exception {
        return obtenerCantidadHorasSuenio(persona.getEdad());
    }
}
