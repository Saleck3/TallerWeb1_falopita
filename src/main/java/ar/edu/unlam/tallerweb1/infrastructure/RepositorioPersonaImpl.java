package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioPersona")
public class RepositorioPersonaImpl implements RepositorioPersona {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Persona obtener(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Persona.class, id);
    }

    @Override
    public Long guardar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();

        return (Long) session.save(persona);
    }

    @Override
    public void modificar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        session.update(persona);
    }

    @Override
    public void eliminar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(persona);
    }
}
