package ar.edu.unlam.tallerweb1.infrastructure.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;

import java.util.List;

public interface RepositorioSuenio {
    List<RegistroSuenio> obtener(Persona Persona);

    RegistroSuenio obtener(Long id);

    RegistroSuenio guardar(RegistroSuenio registro);

    void modificar(RegistroSuenio registro);

    void eliminar(RegistroSuenio registro);

}
