package net.dovale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractCRUDDao<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;
    private final String entityName;

    protected AbstractCRUDDao(SessionFactory sessionFactory, Class<T> entityClass, String entityName) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
        this.entityName = entityName;
    }

    public T save(T entity){
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    public void delete(T entity){
        sessionFactory.getCurrentSession().delete(entity);
    }

    public T find(long id){
        return sessionFactory.getCurrentSession().find(entityClass, id);
    }

    public List<T> list(){
            String query = String.format("FROM %s", entityName);
            return sessionFactory
                    .getCurrentSession()
                    .createQuery(query)
                    .list();
    }
}
