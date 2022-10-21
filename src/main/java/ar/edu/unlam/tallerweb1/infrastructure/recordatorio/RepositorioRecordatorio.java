package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;

import java.util.List;

public interface RepositorioRecordatorio {

    Recordatorio guardar(Recordatorio recordatorio);
    List<Recordatorio> listar(Persona persona);
}
