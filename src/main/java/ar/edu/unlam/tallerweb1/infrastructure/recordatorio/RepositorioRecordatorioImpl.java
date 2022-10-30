package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Recordatorio guardar(Recordatorio recordatorio) {
        session = sessionFactory.getCurrentSession();
        session.save(recordatorio);
        return recordatorio;
    }

    @Override
    public void eliminar(Recordatorio recordatorio) {
        session = sessionFactory.getCurrentSession();
        session.delete(recordatorio);
    }

    @Override
    public Recordatorio modificar(Recordatorio recordatorio) {
        session = sessionFactory.getCurrentSession();
        session.update(recordatorio);
        return recordatorio;
    }
}
