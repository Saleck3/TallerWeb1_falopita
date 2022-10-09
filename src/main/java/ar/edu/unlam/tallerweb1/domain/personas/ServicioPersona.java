package ar.edu.unlam.tallerweb1.domain.personas;

public interface ServicioPersona {
    Double calcularHorasDeSuenio(Persona persona);

    Persona obtenerPersona(Long id);

    Persona obtenerPersona(String email, String password);

    void modificarPersona(Persona personaAModificar);

    boolean validarPersona(Persona personaAValidar);

    void guardarPersona(Persona personaAGuardar);
}
