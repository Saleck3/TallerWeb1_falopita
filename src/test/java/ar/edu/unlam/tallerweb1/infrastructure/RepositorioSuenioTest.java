package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;
import ar.edu.unlam.tallerweb1.infrastructure.suenio.RepositorioSuenio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;


@Transactional
public class RepositorioSuenioTest extends SpringTest {

    @Autowired
    private RepositorioSuenio repositorioSuenio;

    @Test
    public void queCuandregistroTraigaLaCantidadCorrecta() {
        Persona persona = dadoQueTengoUnaPersona();

        cuandoTengoRegistro(persona);
        cuandoTengoRegistro(persona);

        entoncesTengoRegistros(persona, 2);
    }


    @Test
    public void queCuandoEliminoTraigaLaCantidadCorrecta() {
        Persona persona = dadoQueTengoUnaPersona();

        cuandoTengoRegistro(persona);
        RegistroSuenio registroAEliminar = cuandoTengoRegistro(persona);
        cuandoEliminoRegistro(registroAEliminar);

        entoncesTengoRegistros(persona, 1);
    }

    @Test
    public void queLaDiferenciaDeHorasSeaCorrecta() {
        Persona persona = dadoQueTengoUnaPersona();
        Long cantidadHorasDiferencia = 2L;
        RegistroSuenio registroConDiferencia = cuandoTengoRegistroConDiferenciaDehoras(persona, cantidadHorasDiferencia);
        entoncesLaCantidadDeHorasEs(registroConDiferencia, cantidadHorasDiferencia);
    }


    private Persona dadoQueTengoUnaPersona() {
        return new Persona("persona1@example.com", "12345678", "Nombre 1", 23, 60.4, 170.70, 'M');
    }

    private RegistroSuenio cuandoTengoRegistro(Persona persona) {
        LocalDateTime horaInicio = LocalDateTime.now();
        LocalDateTime horaFin = LocalDateTime.now();
        RegistroSuenio registro = new RegistroSuenio(persona, horaInicio, horaFin);
        repositorioSuenio.guardar(registro);
        return registro;
    }

    private void cuandoEliminoRegistro(RegistroSuenio registroAEliminar) {
        repositorioSuenio.eliminar(registroAEliminar);
    }

    private void entoncesTengoRegistros(Persona persona, int cantidadEsperada) {
        assertThat(repositorioSuenio.obtener(persona).size()).isEqualTo(cantidadEsperada);
    }

    private RegistroSuenio cuandoTengoRegistroConDiferenciaDehoras(Persona persona, Long cantidadHorasDiferencia) {

        LocalDateTime horaInicio = LocalDateTime.now();
        LocalDateTime horaFin = LocalDateTime.now().plusHours(cantidadHorasDiferencia);
        RegistroSuenio registro = new RegistroSuenio(persona, horaInicio, horaFin);
        repositorioSuenio.guardar(registro);
        return registro;
    }

    private void entoncesLaCantidadDeHorasEs(RegistroSuenio registroConDiferencia, Long cantidadHorasDiferencia) {
        assertThat(registroConDiferencia.getCantidadHoras()).isEqualTo(cantidadHorasDiferencia);
    }
}
