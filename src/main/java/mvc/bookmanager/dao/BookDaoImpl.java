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

@Repository(value = "hiber")
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
        logger.info("Book successfully saved. Book details: " + book);
    }

    @Override
    public void updateBook(Book book) {
        logger.info("updateBook");
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully update. Book details: " + book);
    }

    @Override
    public void removeBook(int id) {
        logger.info("removeBook");
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);

        if (book != null) {
            session.delete(book);
        }
        logger.info("Book successfully removed. Book details: " + book);
    }

    @Override
    public Book getBookById(int id) {
        logger.info("getBookById");
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        for(Author author: book.getAuthors()){
            session.get(Author.class, author.getId());
        }
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        logger.info("listBooks");
        Session session = this.sessionFactory.getCurrentSession();
       /* List<Book> bookList = session.createQuery("from Book").list();*/
        List<Book> bookList = session.createQuery("select distinct b from Book b left join fetch b.authors")
                .list();
      /*  for (Book book:bookList){
            for (Author author:book.getAuthors()){
            session.get(Author.class, author.getId());
        }}*/
        logger.info("listBooks successfully loaded ");
        return bookList;
    }
}
