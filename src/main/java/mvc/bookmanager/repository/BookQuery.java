package mvc.bookmanager.repository;

import mvc.bookmanager.model.Author;
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
        String first;
        String last;
        boolean b = false;
        for (Author author : book.getAuthors()) {
            if (b == true) return b;
            first = author.getFirstName();
            last = author.getLastName();
            Query query = session.createQuery("SELECT b FROM Book b JOIN b.authors a " +
                    "WHERE a.lastName=:last AND a.firstName=:first AND b.title=:title");
            query.setParameter("title", book.getTitle());
            query.setParameter("first", first);
            query.setParameter("last", last);
            b = (query.list().size()==0) ? true : false;
        }
        return b;
    }

    public List getTitleByAuthor(String authorName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b.bookTitle FROM Book b WHERE author=:Author");
        query.setParameter("Author", authorName);
        return query.list();
    }


    public List getAuthorByTitle(String titleName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT b.bookAuthor FROM Book b WHERE title=:Title");
        query.setParameter("Title", titleName);
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
        Query query = session.createQuery("SELECT distinct b FROM Book b join fetch b.authors" +
                " WHERE title=:Title");
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


        Query query = session.createQuery("select distinct b from Book b join fetch b.authors a " +
                "where a.lastName IN(:list) or a.firstName IN(:list) order by b.title");
        query.setParameterList("list", listAuthor);
        List<Book> list = query.list();
        return list;
    }

    public List<Book> getBooksByAuthor(Author author) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from Book b join b.authors a where a.lastName= " +
                ":last and a.firstName=:first)");
        query.setParameter("last", author.getLastName());
        query.setParameter("first", author.getFirstName());
        List<Book> list = query.list();
        return list;
    }

    public List<Integer> getBooksByOnlyAuthor(Author author) {
        Session session = this.sessionFactory.getCurrentSession();
   /*     List<Book> bookList = this.getBooksByAuthor(author);*/
        Query query = session.createQuery("select b.id from Book b join b.authors a join b.authors aa " +
                "where aa.id =:Author group by b.id having count(b.id)=1");
        query.setParameter("Author", author.getId());
    /* Query query = session.createSQLQuery("SELECT d.book_id\n" +
             "FROM (SELECT * FROM authors_books ab WHERE ab.author_id = 29 ) as d\n" +
             "JOIN authors_books ab2\n" +
             "ON ab2.book_id = d.book_id\n" +
             "GROUP BY d.book_id\n" +
             "HAVING count(ab2.author_id) = '1';");*/
        return query.list();
    }

    public int getCountBooksByAuthor(String authorName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM Book b WHERE author=:Author");
        query.setParameter("Author", authorName);
        return query.list().size();
    }

}
