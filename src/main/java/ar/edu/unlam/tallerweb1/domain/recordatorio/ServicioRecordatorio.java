package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;

import java.util.List;

public interface ServicioRecordatorio {
    Recordatorio obtenerRecordatorio(Long idRecordatorio);
    Recordatorio crearRecordatorio(DatosRecordatorio datosRecordatorio, Long idPersonaAsociada);
    void eliminarRecordatorio(Long idRecordatorio);
    List<Recordatorio> listarTodos(Long idPersona);
    List<FechaRecordatorio> listarFechasANotificar();
    Integer actualizarEstado();
}
