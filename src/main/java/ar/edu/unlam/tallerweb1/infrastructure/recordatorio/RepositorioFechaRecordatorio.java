package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio;

public interface RepositorioFechaRecordatorio {
    FechaRecordatorio obtener(Long id);
    FechaRecordatorio guardar(FechaRecordatorio fecha);
    void eliminar(FechaRecordatorio fecha);
    FechaRecordatorio modificar(FechaRecordatorio fecha);
}