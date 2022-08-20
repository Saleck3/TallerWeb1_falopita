package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void pruebaConexion() {
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("seba@gmail.com");
        usuario.setPassword("1234");
        usuario.setRol("ADMIN");
        session().save(usuario);
        assertThat(usuario.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaCambiarElEstadoActivo(){
        Usuario usuario = new Usuario();
        session().save(usuario);
        assertThat(usuario.getId()).isNotNull();
        usuario.activar();
        assertTrue(usuario.activo());
        usuario.desactivar();
        assertFalse(usuario.activo());
    }

    @Test
    public void SiTenesUnErrorDeSintaxisNoCompila{

    }
}
