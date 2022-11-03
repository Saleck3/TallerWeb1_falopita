package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio;
import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio.EstadosFecha;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioFechaRecordatorio {
    FechaRecordatorio obtener(Long id);
    FechaRecordatorio guardar(FechaRecordatorio fecha);
    void eliminar(FechaRecordatorio fecha);
    FechaRecordatorio modificar(FechaRecordatorio fecha);
    List<FechaRecordatorio> listarPorFecha(LocalDate fechaFiltro);
    List<FechaRecordatorio> listarPorEstado(EstadosFecha estado);
}