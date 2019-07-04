package mvc.bookmanager.dao;

import mvc.bookmanager.model.Genre;
import mvc.bookmanager.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "hiberPublisher")
public class PublisherDaoImpl implements PublisherDao {
    private static final Logger logger = LoggerFactory.getLogger(PublisherDaoImpl.class);

    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier(value = "hibernate4AnnotatedSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


/*
    @Override
    public void addAuthor(Author author) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(author);
        logger.info("Book successfully saved. Book details: " + author);
    }

    @Override
    public void updateAuthor(Author author) {
        logger.info("updateAuthor");
        Session session = this.sessionFactory.getCurrentSession();
        session.update(author);
        logger.info("Author successfully update. Author details: " + author);
    }

    @Override
    public void removeAuthor(int id) {
        logger.info("removeAuthor");
        Session session = this.sessionFactory.getCurrentSession();
        Author author = (Author) session.get(Author.class, id);
        if (author != null) {
            session.delete(author);
        }
        logger.info("Author successfully removed. Author details: " + author);
    }
*/
    @Override
    public Publisher getPublisherById(int id) {
        logger.info("getPublisherById");
        Session session = this.sessionFactory.getCurrentSession();
        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        logger.info("Publisher successfully loaded. Publisher details: " + publisher);

        return publisher;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Publisher> listPublishers() {
        logger.info("listPublishers");
        Session session = this.sessionFactory.getCurrentSession();
        List<Publisher> publisherList = session.createQuery("from Publisher").list();
        logger.info("listPublishers successfully loaded ");
        return publisherList;
    }
}
