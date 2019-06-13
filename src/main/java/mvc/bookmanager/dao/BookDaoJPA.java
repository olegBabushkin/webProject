package mvc.bookmanager.dao;

import mvc.bookmanager.model.Book;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Oleg
 * 27.04.2019
 */
/*@Repository(value = "jpa")
@Transactional(readOnly = true)*/
public class BookDaoJPA implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoJPA.class);

/*    @PersistenceContext*/
    private EntityManager em;

    @Override
    public void addBook(Book book) {
        if (book.isNew()) {
            em.persist(book);
        } else {
            em.merge(book);
        }

        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        em.merge(book);
        logger.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        Book book = em.find(Book.class, id);
        if (book != null)
            em.remove(book);
        logger.info("Book successfully removed. Book details: " + book);
    }

    @Override
    public Book getBookById(int id) {
        Book book = em.find(Book.class, id);
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        List<Book> bookList = em.createQuery("FROM Book").getResultList();

        for (Book book : bookList) {
            logger.info("Book list: " + book);
        }

        return bookList;
    }

}
