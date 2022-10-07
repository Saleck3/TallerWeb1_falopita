package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public interface ServicioPersona {
    Double calcularHorasDeSuenio(Persona persona);

    public Persona obtenerPersona(Long id);

    public Persona obtenerPersona(String email, String password);

    boolean validarPersona(Persona personaAValidar);
}
