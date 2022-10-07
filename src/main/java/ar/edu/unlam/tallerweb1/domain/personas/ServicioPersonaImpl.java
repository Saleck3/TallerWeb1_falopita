package ar.edu.unlam.tallerweb1.domain.personas;

import ar.edu.unlam.tallerweb1.infrastructure.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.*;

@Service("servicioPersona")
@Transactional
public class ServicioPersonaImpl implements ServicioPersona {

    @Autowired
    private RepositorioPersona repositorioPersona;

    @Override
    public Double calcularHorasDeSuenio(Persona persona) {
        return null;
    }

    @Override
    public Persona obtenerPersona(Long id) {

        return repositorioPersona.obtener(id);
    }

    @Override
    public Persona obtenerPersona(String email, String password) {
        return repositorioPersona.obtener(email, password);
    }

    @Override
    public boolean validarPersona(Persona personaAValidar) {

        //Tiene que si o si tener mail, nombre, sexo, password, edad
        if (personaAValidar.getEmail() == null || personaAValidar.getPassword() == null || personaAValidar.getNombre() == null || personaAValidar.getEdad() == null || personaAValidar.getSexo() == null) {
            return false;
        }


        //Debe tener Mail
        if (!esMailValido(personaAValidar.getEmail())) {
            return false;
        }


        //Debe tener nombre
        if (personaAValidar.getNombre() == "") {
            return false;
        }


        //No puede tener menos de 0 años ni mas de 120
        if (personaAValidar.getEdad() < 0 || personaAValidar.getEdad() > 120) {
            return false;
        }

        //Puede no tener peso
        if (personaAValidar.getPeso() != null) {
            //No puede pesar menos de 10 Kg ni mas de 200 Kg
            if (personaAValidar.getPeso() < 10 || personaAValidar.getPeso() > 200) {
                return false;
            }
        }


        //Puede no tener altura
        if (personaAValidar.getAltura() != null) {
            //No puede medir menos de 40 Cm ni mas de 200
            if (personaAValidar.getAltura() < 40 || personaAValidar.getAltura() > 200) {
                return false;
            }
        }


        //Sexo debe ser 'm', 'f' o 'o'
        //Valido por la negacion
        if (personaAValidar.getSexo() != 'm' && personaAValidar.getSexo() != 'f' && personaAValidar.getSexo() != 'o') {
            return false;
        }


        return true;
    }

    @Override
    public void guardarPersona(Persona personaAGuardar) {
        repositorioPersona.guardar(personaAGuardar);
    }

    //Robado de internet, probablemente mejorable
    public static boolean esMailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

}