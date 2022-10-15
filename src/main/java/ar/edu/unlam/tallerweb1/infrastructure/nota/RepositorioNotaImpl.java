package ar.edu.unlam.tallerweb1.infrastructure.nota;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioNota")
public class RepositorioNotaImpl implements RepositorioNota {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public RepositorioNotaImpl() {
    }

    @Override
    public Nota obtener(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(Nota.class, id);
    }

    @Override
    public Nota guardar(Nota notaAGuardar) {
        session = sessionFactory.getCurrentSession();
        session.save(notaAGuardar);
        return notaAGuardar;
    }

    @Override
    public void modificar(Nota notaAGuardar) {
        session = sessionFactory.getCurrentSession();
        session.update(notaAGuardar);
    }

    @Override
    public void eliminar(Nota notaAEliminar) {
        notaAEliminar.eliminar();
        modificar(notaAEliminar);
    }

    @Override
    public List listarTodas(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .add(Restrictions.eq("persona", persona))
                .add(Restrictions.not(Restrictions.eq("estado", Nota.estadosPosibles.ELIMINADO)))
                .list();
    }

    @Override
    public List listarActivas(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .add(Restrictions.eq("estado", Nota.estadosPosibles.ACTIVO))
                .add(Restrictions.eq("persona", persona))
                .list();
    }

    @Override
    public List listarAncladas(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class).add(Restrictions.eq("estado", Nota.estadosPosibles.ANCLADO)).add(Restrictions.eq("persona", persona)).list();
    }

    @Override
    public List<Nota> listarArchivadas(Persona persona) {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class).add(Restrictions.eq("estado", Nota.estadosPosibles.ARCHIVADO)).add(Restrictions.eq("persona", persona)).list();
    }
}
