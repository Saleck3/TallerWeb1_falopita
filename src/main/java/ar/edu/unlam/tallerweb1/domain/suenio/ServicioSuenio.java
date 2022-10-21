package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.List;

public interface ServicioSuenio {
    public ValorRecomendado obtenerCantidadHorasSuenio(Integer edad) throws Exception;
    public ValorRecomendado obtenerCantidadHorasSuenio(Persona persona) throws Exception;

    RegistroSuenio guardar(RegistroSuenio registroSuenioAGuardar);

    List<RegistroSuenio> obtener(Persona persona);

    void eliminar(RegistroSuenio registroSuenio);
}
