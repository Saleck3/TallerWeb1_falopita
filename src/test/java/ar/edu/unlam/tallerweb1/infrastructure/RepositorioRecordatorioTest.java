package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorioImpl;
import org.hibernate.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Rollback
public class RepositorioRecordatorioTest extends SpringTest {

    private RepositorioRecordatorio repositorioRecordatorio;

    @Before
    public void init(){
        repositorioRecordatorio = new RepositorioRecordatorioImpl();
    }

    @Test
    public void dadoQueTengoUnRecordatorioObtengoElRecordatorio(){
        dadoQueTengoUnRecordatorioConSuFecha();
        List<Recordatorio> recordatorios = obtengoLosRecordatorios();

        assertThat(recordatorios).hasSize(1);
        assertThat(recordatorios.get(0).getFechas()).hasSize(3);
    }

    @Test
    public void dadoQueTengoVariosRecordatoriosObtengoLosRecordatorios(){
        dadoQueTengoRecordatoriosConSusFechas();
        List<Recordatorio> recordatorios = obtengoLosRecordatorios();

        assertThat(recordatorios).hasSize(2);
        assertThat(recordatorios.get(0).getFechas()).hasSize(3);
        assertThat(recordatorios.get(1).getFechas()).hasSize(4);
    }

    @Test
    public void dadoQueEliminoUnRecordatorioSeEliminanSusFechas(){
        Recordatorio recordatorio = dadoQueTengoUnRecordatorioConSuFecha();

        session().remove(recordatorio);

        List<FechaRecordatorio> fechas = obtengoLasFechasDelRecordatorio();

        assertThat(fechas).hasSize(0);
    }

    @Test
    public void dadoQueEliminoUnaFechaDeUnRecordatorioNoSeEliminaElRecordatorio(){
        Recordatorio recordatorio = dadoQueTengoUnRecordatorioConSuFecha();

        FechaRecordatorio fechaPuntual = recordatorio.getFechas().get(0);

        recordatorio.getFechas().remove(fechaPuntual);
        session().remove(fechaPuntual);

        session().flush();

        recordatorio = session().get(Recordatorio.class, 1L);

        assertThat(recordatorio.getFechas()).hasSize(2);
        List<FechaRecordatorio> listaFechas = obtengoLasFechasDelRecordatorio();
        assertThat(listaFechas).hasSize(2);
    }
    private List<Recordatorio> obtengoLosRecordatorios() {
        Criteria cr = session().createCriteria(Recordatorio.class);
        return cr.list();
    }
    private List<FechaRecordatorio> obtengoLasFechasDelRecordatorio() {
        Criteria cr = session().createCriteria(FechaRecordatorio.class);
        return cr.list();
    }

    private Recordatorio dadoQueTengoUnRecordatorioConSuFecha() {
        List<FechaRecordatorio> listaFechas = new ArrayList<>();

        listaFechas.add(new FechaRecordatorio());
        listaFechas.add(new FechaRecordatorio());
        listaFechas.add(new FechaRecordatorio());

        Recordatorio recordatorio = new Recordatorio(listaFechas);

        session().persist(recordatorio);

        return recordatorio;
    }

    private List<Recordatorio> dadoQueTengoRecordatoriosConSusFechas() {
        List<FechaRecordatorio> listaFechas1 = new ArrayList<>();
        listaFechas1.add(new FechaRecordatorio());
        listaFechas1.add(new FechaRecordatorio());
        listaFechas1.add(new FechaRecordatorio());

        Recordatorio recordatorio1 = new Recordatorio(listaFechas1);
        session().persist(recordatorio1);

        List<FechaRecordatorio> listaFechas2 = new ArrayList<>();
        listaFechas2.add(new FechaRecordatorio());
        listaFechas2.add(new FechaRecordatorio());
        listaFechas2.add(new FechaRecordatorio());
        listaFechas2.add(new FechaRecordatorio());


        Recordatorio recordatorio2 = new Recordatorio(listaFechas2);
        session().persist(recordatorio2);

        List<Recordatorio> recordatorios = new ArrayList<>();
        recordatorios.add(recordatorio1);
        recordatorios.add(recordatorio2);

        return recordatorios;
    }

    @Test
    public void test(){
        Persona persona = new Persona();
        session().persist(persona);

        Recordatorio recordatorio = new Recordatorio();
        session().persist(recordatorio);

        recordatorio.setPersona(persona);

        FechaRecordatorio fechaRecordatorio1 = new FechaRecordatorio();
        FechaRecordatorio fechaRecordatorio2 = new FechaRecordatorio();

        session().persist(fechaRecordatorio1);
        session().persist(fechaRecordatorio2);

        recordatorio.agregarFecha(fechaRecordatorio1);
        recordatorio.agregarFecha(fechaRecordatorio2);


        session().flush();
        session().clear();

        Recordatorio recordatorio1 = session().get(Recordatorio.class, 1L);
        assertThat(recordatorio1.getFechas()).hasSize(2);
        assertThat(recordatorio1.getFechas().get(0).getId()).isEqualTo(1L);
        assertThat(recordatorio1.getFechas().get(1).getId()).isEqualTo(2L);
    }
}
