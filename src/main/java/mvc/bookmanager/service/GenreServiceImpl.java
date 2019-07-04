package mvc.bookmanager.service;

import mvc.bookmanager.dao.AuthorDao;
import mvc.bookmanager.dao.GenreDao;
import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;
    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    @Qualifier(value = "hiberGenre")
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

  /*  @Override
    @Transactional
    public void addBook(Book book) throws AppException {
        logger.info("saveBook");
        if (true) {//(this.bookQuery.getEqualsBooks(book)) {
            this.bookDao.addBook(book);
        } else {
            logger.error("alreadyexist");
            throw new AppException("This book's title and authors already exist");
        }
    }

    @Override
    @Transactional
    public void editBook(Book book) throws AppException {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void findBookByAuthor(String name) {
        this.bookQuery.getTitleByAuthor(name);
    }


    @Override
    @Transactional
    public List<Book> findBook(DTOSearch search) {

        if (search.getFind().equals("title")) {
            return this.bookQuery.getBookByTitle(search.getText());
        }
        if (search.getFind().equals("author")) {
            return this.bookQuery.getBookByAuthor(search.getText());
        } else throw new AppException("The choice is not selected");
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> listBooks() {
        return this.bookDao.listBooks();
    }*/

  /*  @Override
    @Transactional
    public void addGenre(Genre genre) throws AppException {
        logger.info("saveGenre");
        if (true) {//(this.bookQuery.getEqualsBooks(book)) {
            this.genreDao.addAuthor(author);
        } else {
            logger.error("alreadyexist");
            throw new AppException("This author's title and authors already exist");
        }
    }*/


    @Override
    public void addGenre(Genre genre) throws AppException {

    }

    @Override
    public void editGenre(Genre genre) throws AppException {

    }

    @Override
    public void updateGenre(Genre genre) {

    }

    @Override
    public void removeGenre(int id) {

    }

    @Transactional
    @Override
    public Genre getGenreById(int id) {
        return this.genreDao.getGenreById(id);
    }

    @Transactional
    @Override
    public List<Genre> listGenres() {
        return this.genreDao.listGenres();
    }
}
