package ar.edu.unlam.tallerweb1.infrastructure.suenio;

import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;

import java.util.List;

public interface RepositorioSuenio {

    List<RegistroSuenio> obtener(Long idPersona);
    RegistroSuenio guardar(RegistroSuenio registro);
    void modificar(RegistroSuenio registro);
    void eliminar(RegistroSuenio registro);

}
