package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.List;

public interface ServicioSuenio {
    ValorRecomendado obtenerCantidadHorasSuenio(Persona persona) throws EdadNegativaException;

    Double cantidadHorasDormidaEnLosUltimosXDias(Persona persona, Long cantidadDias) throws NoTieneRegistroDeSuenio;

    RegistroSuenio guardarRegistroSuenio(RegistroSuenio registroSuenioAGuardar);

    List<RegistroSuenio> obtenerRegistrosSuenio(Persona persona);

    RegistroSuenio obtenerRegistroSuenio(Long id);

    void eliminarRegistroSuenio(RegistroSuenio registroSuenio);

    List<RegistroSuenio> registrosDeLosUltimosxDias(Persona persona, Integer cantidadDias) throws
            NoTieneRegistroDeSuenio;
}
