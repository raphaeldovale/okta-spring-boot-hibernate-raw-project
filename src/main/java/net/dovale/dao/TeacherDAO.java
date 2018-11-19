package net.dovale.dao;

import org.hibernate.SessionFactory;
import net.dovale.entities.Teacher;

public class TeacherDAO extends AbstractCRUDDao<Teacher> {

    public TeacherDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Teacher.class, "Teacher");
    }
}
