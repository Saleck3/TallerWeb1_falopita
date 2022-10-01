package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioPersona")
@Transactional
public class ServicioPersonaImpl implements ServicioPersona{

    @Autowired
    private RepositorioUsuario repositorioPersona;

    @Override
    public Double calcularHorasDeSuenio(Persona persona){
        return null;
    }

    @Override
    public Persona obtenerPersona(Long id) {
        return null;
    }
    @Override
    public Persona obtenerPersona(String email, String password) {
        return null;
    }

}