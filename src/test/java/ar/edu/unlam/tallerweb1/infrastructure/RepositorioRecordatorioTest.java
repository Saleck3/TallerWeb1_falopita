package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorioImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
public class RepositorioRecordatorioTest {

    private RepositorioRecordatorio repositorioRecordatorio;

    @Before
    public void init(){
        repositorioRecordatorio = new RepositorioRecordatorioImpl();
    }
}
