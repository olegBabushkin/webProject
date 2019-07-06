package mvc.bookmanager.repository;

import mvc.bookmanager.model.Role;
import mvc.bookmanager.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Oleg
 * 26.04.2019
 */
@Component
public class RoleQuery {

    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Role getRoleById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role)session.get(Role.class, id);
        return role;
    }
}
