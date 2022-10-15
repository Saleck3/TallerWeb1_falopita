package ar.edu.unlam.tallerweb1.infrastructure.persona;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import java.util.List;

public interface RepositorioPersona {

    Persona obtener(Long id);

    Long guardar(Persona persona);

    void modificar(Persona persona);

    void eliminar(Persona persona);

    List<Persona> listar();

    Persona obtener(String email, String password);
}
