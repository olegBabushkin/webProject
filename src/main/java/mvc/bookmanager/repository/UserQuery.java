package mvc.bookmanager.repository;

import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Oleg
 * 26.04.2019
 */
@Component
public class UserQuery {

    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public User getUserByName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT u FROM User u join fetch u.roles " +
                "WHERE username=:UserName");
        query.setParameter("UserName", userName);
        return (User)query.uniqueResult();
    }
}
