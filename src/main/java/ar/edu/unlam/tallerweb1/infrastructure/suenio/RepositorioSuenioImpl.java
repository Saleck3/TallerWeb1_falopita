package ar.edu.unlam.tallerweb1.infrastructure.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.suenio.RegistroSuenio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioSuenio")
public class RepositorioSuenioImpl implements RepositorioSuenio {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public RepositorioSuenioImpl() {
    }

    @Override
    public List obtener(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(RegistroSuenio.class)
                .add(Restrictions.eq("persona", persona))
                .add(Restrictions.eq("eliminado", false))
                .list();
    }

    @Override
    public RegistroSuenio obtener(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(RegistroSuenio.class, id);
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
        registro.eliminar();
        session.update(registro);
    }
}
