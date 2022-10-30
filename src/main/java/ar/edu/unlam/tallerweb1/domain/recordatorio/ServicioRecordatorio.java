package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;

public interface ServicioRecordatorio {
    Recordatorio obtenerRecordatorio(Long idRecordatorio);
    Recordatorio crearRecordatorio(DatosRecordatorio datosRecordatorio, Long idPersonaAsociada);
    void eliminarRecordatorio(Long idRecordatorio);

}
