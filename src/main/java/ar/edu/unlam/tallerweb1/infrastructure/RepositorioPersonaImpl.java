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
    public Persona obtener(String nombre) {
        return null;
    }

    @Override
    public Persona obtener(Double peso) {
        return null;
    }

    @Override
    public Persona obtener(Integer edad) {
        return null;
    }

    @Override
    public Persona obtener(Character sexo) {
        return null;
    }

    @Override
    public void guardar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        session.save(persona);
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
