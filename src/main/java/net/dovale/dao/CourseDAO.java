package net.dovale.dao;

import net.dovale.entities.Course;
import org.hibernate.SessionFactory;

public class CourseDAO extends AbstractCRUDDao<Course> {
    public CourseDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Course.class, "Course");
    }
}
