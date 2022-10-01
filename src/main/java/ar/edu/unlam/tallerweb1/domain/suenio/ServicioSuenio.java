package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.ValorRecomendado;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public interface ServicioSuenio {
    public ValorRecomendado obtenerCantidadHorasSuenio(Integer edad) throws Exception;
    public ValorRecomendado obtenerCantidadHorasSuenio(Persona persona) throws Exception;
}
