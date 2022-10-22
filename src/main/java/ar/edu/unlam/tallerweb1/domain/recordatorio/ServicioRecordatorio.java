package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.Date;
import java.util.List;

public interface ServicioRecordatorio {

    Recordatorio obtenerRecordatorio(Long idRecordatorio);
    Recordatorio crearRecordatorio(DatosRecordatorio datosRecordatorio, Long idPersonaAsociada);
    void eliminarRecordatorio(Long idRecordatorio);
    List<Recordatorio> listarRecordatorios(Long idPersonaAsociada);
    List<Recordatorio> listarRecordatorios(Long idPersona, Date fechaFiltro);
    Recordatorio ocultarRecordatorio(Long idRecordatorio);

}
