package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;

import java.util.List;

public interface RepositorioRecordatorio {
    Recordatorio obtener(Long id);
    Recordatorio guardar(Recordatorio recordatorio);
    void eliminar(Recordatorio recordatorio);
    Recordatorio modificar(Recordatorio recordatorio);
    List<Recordatorio> listarPorPersona(Persona persona);
}
