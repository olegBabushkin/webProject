package mvc.bookmanager.repository;

import mvc.bookmanager.model.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oleg
 * 26.04.2019
 */
@Component
public class BookQuery {

    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean getEqualsBooks(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b FROM Book b WHERE bookTitle=:Title " +
                "AND bookAuthor=:Author");
        query.setParameter("Title", book.getBookTitle());
        query.setParameter("Author", book.getBookAuthor());

        return query.uniqueResult() == null;
    }

    public List getTitleByAuthor(String authorName){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b.bookTitle FROM Book b WHERE bookAuthor=:Author");
        query.setParameter("Author", authorName);
        return query.list();
    };

    public List getAuthorByTitle(String titleName){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b.bookAuthor FROM Book b WHERE bookTitle=:Title");
        query.setParameter("Title", titleName);
        return query.list();
    };

    public List<Book> getBookByTitle(String title){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b FROM Book b WHERE bookTitle=:Title");
        query.setParameter("Title", title);
        return query.list();
    };

    public List<Book> getBookByAuthor(String author){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b FROM Book b WHERE bookAuthor=:Author");
        query.setParameter("Author", author);
        return query.list();
    };

    public int getCountBooksByAuthor(String authorName){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM Book b WHERE bookAuthor=:Author");
        query.setParameter("Author", authorName);
        return query.list().size();
    }

}
