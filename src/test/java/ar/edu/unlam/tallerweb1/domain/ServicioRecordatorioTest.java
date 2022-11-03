package ar.edu.unlam.tallerweb1.domain;

import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.ServicioRecordatorio;
import ar.edu.unlam.tallerweb1.domain.recordatorio.ServicioRecordatorioImpl;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioFechaRecordatorio;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ServicioRecordatorioTest {

    private ServicioRecordatorio servicioRecordatorio;

    @Mock
    RepositorioRecordatorio repositorioRecordatorioMock;
    @Mock
    RepositorioFechaRecordatorio repositorioFechaRecordatorioMock;
    @Mock
    ServicioPersona servicioPersonaMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        servicioRecordatorio = new ServicioRecordatorioImpl(servicioPersonaMock,
                repositorioRecordatorioMock,
                repositorioFechaRecordatorioMock);
    }

    @Test
    public void test(){
        LocalDate hoy = LocalDate.now();
        assertThat(hoy.toString()).isEqualTo("2022-11-02");
    }

    @Test
    public void dadoIdDeRecordatorioSeObtieneElRecordatorio(){
    }

    @Test
    public void dadoDatosDeRecordatorioYIdDePersonaSeCreaUnRecordatorio(){
    }

    @Test
    public void dadoDatosDeRecordatorioYIdDePersonaModificoSeModificaUnRecordatorio(){

    }

    @Test
    public void dadoIdDeRecordatorioSeEliminaUnRecordatorio(){

    }

    @Test
    public void dadoIdDeRecordatorioSeAgreganFechasAlRecordatorio(){

    }

    @Test
    public void dadoIdDeRecordatorioSeModificanLasFechasDelRecordatorio(){

    }

    @Test
    public void dadoIdDeRecordatorioSeEliminanFechasDelRecordatorio(){

    }

    @Test
    public void dadoIdDeFechaSeMarcaFechaParaQueNoSeVuelvaARecordar(){
    }

    @Test
    public void dadoIdDeRecordatorioSePuedenAgregarEtiquetas(){
    }

    @Test
    public void dadoIdDeRecordatorioSePuedenEliminarEtiquetas(){
    }

    /*
    @Test
    public void dadoUnaFechaCalculoLaSiguienteSegunFrecuencia(){
        Integer anio = 2022;
        Integer mes = 10;
        Integer dia = 15;
        LocalDate fecha = dadoQueTengoUnaFecha(anio, mes, dia);
        Recordatorio.TipoFrecuencia frecuencia = Recordatorio.TipoFrecuencia.MENSUAL;

        LocalDate fechaSiguiente = servicioRecordatorio.calcularFechaSiguente(fecha);

        assertThat(fechaSiguiente.getYear()).isEqualTo(anio);
        assertThat(fechaSiguiente.getMonthValue()).isEqualTo(mes + 1);
        assertThat(fechaSiguiente.getDayOfMonth()).isEqualTo(dia);
    }

    private LocalDate dadoQueTengoUnaFecha(Integer anio, Integer mes, Integer dia) {
        return LocalDate.of(anio,mes, dia);
    }
    */
}
