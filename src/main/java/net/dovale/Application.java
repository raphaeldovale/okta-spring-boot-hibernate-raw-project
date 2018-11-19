package net.dovale;

import net.dovale.dao.CourseDAO;
import net.dovale.dao.TeacherDAO;
import net.dovale.entities.Course;
import net.dovale.entities.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Application {

    public static void main(String[] args){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try (SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory()
        ) {

            CourseDAO courseDAO = new CourseDAO(sessionFactory);
            TeacherDAO teacherDAO = new TeacherDAO(sessionFactory);

            try (Session session = sessionFactory.getCurrentSession()) {

                Transaction tx = session.beginTransaction();

                // create teachers
                Teacher pj = new Teacher(
                        "Profesor Jirafales",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Ruben2017.jpg/245px-Ruben2017.jpg",
                        "jirafales@yahoo_.com"
                );

                Teacher px = new Teacher(
                        "Professor X",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9uI1Cb-nQ2uJOph4_t96KRvLSMjczAKnHLJYi1nqWXagvqWc4",
                        "director@xproject_.com"

                );

                teacherDAO.save(pj);
                teacherDAO.save(px);

                // create courses
                Course mathematics = new Course("Mathematics", 20, (short) 10, pj);
                Course spanish = new Course("Spanish", 20, (short) 10, pj);
                Course dealingWithUnknown = new Course("Dealing with unknown", 10, (short) 100, pj);
                Course handlingYourMentalPower = new Course("Handling your mental power", 50, (short) 100, pj);
                Course introductionToPsychology = new Course("Introduction to psychology", 90, (short) 100, pj);

                courseDAO.save(mathematics);
                courseDAO.save(spanish);
                courseDAO.save(dealingWithUnknown);
                courseDAO.save(handlingYourMentalPower);
                courseDAO.save(introductionToPsychology);

                tx.commit();
            }

            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                System.out.println("Courses");
                courseDAO
                        .list()
                        .forEach(course -> System.out.println(course.getName()));

                System.out.println("Teachers");
                teacherDAO
                        .list()
                        .forEach(teacher -> System.out.println(teacher.getName()));
            }

        }

    }
}
