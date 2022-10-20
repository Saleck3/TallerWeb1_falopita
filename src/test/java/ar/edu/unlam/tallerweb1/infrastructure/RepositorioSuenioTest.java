package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.infrastructure.suenio.RepositorioSuenio;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


@Transactional
public class RepositorioSuenioTest extends SpringTest {

    @Autowired
    private RepositorioSuenio repositorioSuenio;
    private static final int EDAD = 25;


}
