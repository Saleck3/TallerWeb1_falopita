package ar.edu.unlam.tallerweb1.infrastructure.suenio;



import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioSuenioImpl implements RepositorioSuenio{


    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public List<RegistroSuenio> obtener(Long idPersona) {
        return null;
    }

    @Override
    public RegistroSuenio guardar(RegistroSuenio registro) {
        session = sessionFactory.getCurrentSession();
        session.save(registro);
        return registro;
    }

    @Override
    public void modificar(RegistroSuenio registro) {
        session = sessionFactory.getCurrentSession();
        session.update(registro);
    }

    @Override
    public void eliminar(RegistroSuenio registro) {
        session = sessionFactory.getCurrentSession();
        session.save(registro);
    }
}
