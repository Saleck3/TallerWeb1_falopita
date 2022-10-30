package ar.edu.unlam.tallerweb1.infrastructure.recordatorio;

import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioFechaRecordatorio")
public class RepositorioFechaRecordatorioImpl implements RepositorioFechaRecordatorio{

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public FechaRecordatorio obtener(Long id) {
        session = sessionFactory.getCurrentSession();
        return session.get(FechaRecordatorio.class, id);
    }

    @Override
    public FechaRecordatorio guardar(FechaRecordatorio fecha) {
        session = sessionFactory.getCurrentSession();
        session.save(fecha);
        return fecha;
    }

    @Override
    public void eliminar(FechaRecordatorio fecha) {
        session = sessionFactory.getCurrentSession();
        session.delete(fecha);
    }

    @Override
    public FechaRecordatorio modificar(FechaRecordatorio fecha) {
        session = sessionFactory.getCurrentSession();
        session.update(fecha);
        return fecha;
    }
}
