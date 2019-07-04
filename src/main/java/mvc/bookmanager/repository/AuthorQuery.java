package mvc.bookmanager.repository;

import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
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
public class AuthorQuery {

    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

/*    public boolean isBookNullAfterUpdate(Author author) {
        Session session = this.sessionFactory.getCurrentSession();

    }*/



    public boolean getEqualsAuthor(Author author) {
        Session session = this.sessionFactory.getCurrentSession();
       Query query = session.createQuery("Select a from Author a where a.firstName=:FirstName and " +
               "a.lastName=:LastName");
       query.setParameter("FirstName",author.getFirstName() );
       query.setParameter("LastName",author.getLastName() );
       return query.uniqueResult()==null;
    }

    public List getTitleByAuthor(String authorName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b.bookTitle FROM Book b WHERE author=:Author");
        query.setParameter("Author", authorName);
        return query.list();
    }


    public List getAuthorByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b FROM Author b WHERE firstName=:First or " +
                "lastName=:Last");
        query.setParameter("First", name);
        query.setParameter("Last", name);
        return query.list();
    }

    ;

    public int getAuthorIdByHisLastName(String titleName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Author WHERE lastName=:LastName");
        query.setParameter("LastName", titleName);
        return ((Author) query.uniqueResult()).getId();
    }

    ;

    public List<Book> getBookByTitle(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b FROM Book b WHERE title=:Title");
        query.setParameter("Title", title);
        return query.list();
    }

    ;

    public List<Book> getBookByAuthor(List<String> listAuthor) {
        Session session = this.sessionFactory.getCurrentSession();
    /*    int id = this.getAuthorIdByHisLastName(author);
        Query query = session.createQuery("select b from Book b join b.authors a where a.id = " +
                ":authorId order by b.title");
        query.setParameter("authorId", id);*/


        Query query = session.createQuery("select b from Book b join b.authors a where a.lastName " +
                "IN(:list) or a.firstName IN(:list) order by b.title");
        query.setParameterList("list", listAuthor);
        List<Book> list = query.list();
        return list;
    }

    ;

    public int getCountBooksByAuthor(String authorName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM Book b WHERE author=:Author");
        query.setParameter("Author", authorName);
        return query.list().size();
    }

}
