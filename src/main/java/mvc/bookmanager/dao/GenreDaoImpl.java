package mvc.bookmanager.dao;

import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "hiberGenre")
public class GenreDaoImpl implements GenreDao {
    private static final Logger logger = LoggerFactory.getLogger(GenreDaoImpl.class);

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
    public Genre getGenreById(int id) {
        logger.info("getGenreById");
        Session session = this.sessionFactory.getCurrentSession();
        Genre genre = (Genre) session.get(Genre.class, id);
        logger.info("Genre successfully loaded. Genre details: " + genre);

        return genre;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Genre> listGenres() {
        logger.info("listGenres");
        Session session = this.sessionFactory.getCurrentSession();
        List<Genre> genresList = session.createQuery("from Genre").list();
        logger.info("listGenres successfully loaded ");
        return genresList;
    }
}
