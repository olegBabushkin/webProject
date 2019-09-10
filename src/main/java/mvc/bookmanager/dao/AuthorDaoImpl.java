package mvc.bookmanager.dao;

import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "hiberAuthor")
public class AuthorDaoImpl implements AuthorDao {
    private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addAuthor(Author author) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(author);
        logger.info("Book successfully saved. Book details: " + author);
    }

    @Override
    public void updateAuthor(Author author) {
        logger.info("updateAuthor");
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(author);
        logger.info("Author successfully update. Author details: " + author);
    }

    @Override
    public void removeAuthor(int id) {
        logger.info("removeAuthor");
        Session session = this.sessionFactory.getCurrentSession();
        Author author = (Author) session.get(Author.class, id);
        for (Book b : author.getBooks()) {
            if (b.getAuthors().size() == 1) {
                session.delete(b);
            } else {
                b.getAuthors().remove(author);
            }
        }
        session.delete(author);
        logger.info("Author successfully removed. Author details: " + author);
    }

    @Override
    public Author getAuthorById(int id) {
        logger.info("getAuthorById");
        Session session = this.sessionFactory.getCurrentSession();
        Author author = (Author) session.get(Author.class, id);
        for(Book book: author.getBooks()){
            session.get(Book.class, book.getId());
        }
        logger.info("Author successfully loaded. Author details: " + author);

        return author;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Author> listAuthors() {
        logger.info("listAuthors");
        Session session = this.sessionFactory.getCurrentSession();
        List<Author> authorList = session.createQuery("select distinct a from Author a left join fetch a.books").list();
        logger.info("listAuthor successfully loaded ");
        return authorList;
    }
}
