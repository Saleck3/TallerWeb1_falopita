package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.List;

public interface ServicioRecordatorio {

    Recordatorio crearRecordatorio(DatosRecordatorio recordatorio, Persona personaAsociada);
    List<Recordatorio> listarRecordatorios(Persona persona);
}
