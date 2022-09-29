package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class RepositorioSuenioImpl implements RepositorioSuenio{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSuenioImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    @Override
    public List<Suenio> TraerPorEdadDeterminada(int edad) {

        final Session session= sessionFactory.getCurrentSession();
        return (List<Suenio>) session.createCriteria(Suenio.class)
                .add(Restrictions.eq("edad", edad))
                .list();
    }

    @Override
    public List<Suenio> listar() {

        final Session session= sessionFactory.getCurrentSession();
        return (List<Suenio>) session.createCriteria(Suenio.class)
                .list();
    }
}
