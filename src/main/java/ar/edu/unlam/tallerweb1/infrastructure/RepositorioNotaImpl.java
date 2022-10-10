package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.nota.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
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
    public Serializable guardar(Nota notaAGuardar) {
        session = sessionFactory.getCurrentSession();
        return session.save(notaAGuardar);
    }

    @Override
    public void modificar(Nota notaAGuardar) {
        session = sessionFactory.getCurrentSession();
        session.update(notaAGuardar);
    }

    @Override
    public void eliminar(Nota notaAEliminar) {
        session = sessionFactory.getCurrentSession();
        session.delete(notaAEliminar);
    }

    @Override
    public List<Nota> listarTodas() {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .list();
    }

    @Override
    public List<Nota> listarActivas() {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .add(Restrictions.eq("estado", Nota.estadosPosibles.ACTIVO))
                .list();
    }

    @Override
    public List<Nota> listarAncladas() {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .add(Restrictions.eq("estado", Nota.estadosPosibles.ANCLADO))
                .list();
    }

    @Override
    public List<Nota> listarArchivadas() {
        session = sessionFactory.getCurrentSession();
        return session.createCriteria(Nota.class)
                .add(Restrictions.eq("estado", Nota.estadosPosibles.ARCHIVADO))
                .list();
    }
}
