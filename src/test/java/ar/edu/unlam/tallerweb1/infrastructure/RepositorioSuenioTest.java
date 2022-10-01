package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;
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

    @Test
    public void segunMiEdadDarMisHorasDeSuenioRecomendadas(){
        dadoQueExistenHorasDeSuenio();
        List<Suenio> listaDeHorasXEdad = listoLasHoraQueNecesitoDormirSegunEdad(EDAD);
        entoncesObtengoLasHorasQueEsperoDormir(listaDeHorasXEdad);

    }

    private List<Suenio> listoLasHoraQueNecesitoDormirSegunEdad(int edad) {
      return this.repositorioSuenio.TraerPorEdadDeterminada(edad);
    }

    private void entoncesObtengoLasHorasQueEsperoDormir(List<Suenio> listaDeHorasXEdad) {
        //TODO: A REVISAR PORQUE SE PUEDE MEJORAR
        Suenio suenio= listaDeHorasXEdad.stream().filter(S->S.getEdad()==25).findFirst().get();//esto es un for manga de energumenos
        assertEquals(6,suenio.getHorasQueNecesitaDormir());
    }

    private void dadoQueExistenHorasDeSuenio() {
        Suenio horasXEdad= new Suenio(15, 8);
        session().save(horasXEdad);

        Suenio horasXEdad1= new Suenio(18, 7);
        session().save(horasXEdad1);

        Suenio horasXEdad2= new Suenio(25, 6);
        session().save(horasXEdad2);
    }

}
