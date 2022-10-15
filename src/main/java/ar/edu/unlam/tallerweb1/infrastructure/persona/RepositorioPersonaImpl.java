package ar.edu.unlam.tallerweb1.infrastructure.persona;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioPersona")
public class RepositorioPersonaImpl implements RepositorioPersona {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public RepositorioPersonaImpl() {
    }

    @Override
    public Persona obtener(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(Persona.class, id);
    }

    @Override
    public Long guardar(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return (Long) session.save(persona);
    }

    @Override
    public void modificar(Persona persona) {
        session = sessionFactory.getCurrentSession();
        session.update(persona);
    }

    @Override
    public void eliminar(Persona persona) {
        session = sessionFactory.getCurrentSession();
        session.delete(persona);
    }

    @Override
    public List<Persona> listar() {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Persona.class)
                .list();
    }

    @Override
    public Persona obtener(String email, String password) {
        session = sessionFactory.getCurrentSession();
        return (Persona) session.createCriteria(Persona.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }
}
