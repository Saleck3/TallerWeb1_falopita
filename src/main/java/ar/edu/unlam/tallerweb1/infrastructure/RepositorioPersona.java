package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public interface RepositorioPersona {

    Persona obtener(Long id);

    Long guardar(Persona persona);

    void modificar(Persona persona);

    void eliminar(Persona persona);
}
