package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;

import java.util.List;

public interface RepositorioRecordatorio {
    Recordatorio obtener(Long id);
    Recordatorio guardar(Recordatorio recordatorioAGuardar);
    void eliminar(Recordatorio recordatorioAEliminar);
    Recordatorio modificar(Recordatorio recordatorioAModificar);
    List<Recordatorio> listar(Persona personaAsociada);
    List<Recordatorio> listar(Persona personaAsociada, DatosRecordatorio datosFiltro);
}
