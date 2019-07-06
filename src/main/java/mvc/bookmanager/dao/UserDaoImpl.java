package mvc.bookmanager.dao;

import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.Role;
import mvc.bookmanager.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. Book details: " + user);
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
    public User getUserById(int id) {
        logger.info("getBookById");
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        for(Role role: user.getRoles()){
            session.get(Role.class, role.getId());
        }
        logger.info("Book successfully loaded. Book details: " + user);

        return user;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

  /*  @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        logger.info("listBooks");
        Session session = this.sessionFactory.getCurrentSession();
       *//* List<Book> bookList = session.createQuery("from Book").list();*//*
        List<Book> bookList = session.createQuery("select distinct b from Book b left join fetch b.authors").list();
      *//*  for (Book book:bookList){
            for (Author author:book.getAuthors()){
            session.get(Author.class, author.getId());
        }}*//*
        logger.info("listBooks successfully loaded ");
        return bookList;
    }*/
}
