package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioRecordatorio")
public class RepositorioRecordatorioImpl implements RepositorioRecordatorio{

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public Recordatorio obtener(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(Recordatorio.class, id);
    }

    @Override
    public Recordatorio guardar(Recordatorio recordatorioAGuardar) {
        session = sessionFactory.getCurrentSession();
        session.save(recordatorioAGuardar);
        return recordatorioAGuardar;
    }

    @Override
    public List<Recordatorio> listar(Persona persona) {
        session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Recordatorio.class);
        cr.add(Restrictions.eq("persona", persona));
        cr.add(Restrictions.eq("eliminado", false));
        return cr.list();
    }
}
