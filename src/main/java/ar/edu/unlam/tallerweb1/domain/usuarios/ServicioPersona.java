package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public interface ServicioPersona {
    Double calcularHorasDeSuenio(Persona persona);

    public Persona obtenerPersona(Long id);
}
