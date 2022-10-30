package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;

public interface RepositorioRecordatorio {
    Recordatorio obtener(Long id);
    Recordatorio guardar(Recordatorio recordatorio);
    void eliminar(Recordatorio recordatorio);
    Recordatorio modificar(Recordatorio recordatorio);
}
