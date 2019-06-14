package mvc.bookmanager.service;

import mvc.bookmanager.dao.BookDao;
import mvc.bookmanager.dao.BookDaoImpl;
import mvc.bookmanager.dto.DTOSearch;
import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Book;

import mvc.bookmanager.repository.BookQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private BookQuery bookQuery;
    private BookDao bookDao;
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    @Qualifier(value = "hiber")
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setBookQuery(BookQuery bookQuery) {
        this.bookQuery = bookQuery;
    }


    @Override
    @Transactional
    public void addBook(Book book) throws AppException {
        logger.info("saveBook");
        if (this.bookQuery.getEqualsBooks(book)) {
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

        if (search.getFind().equals("title")){
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
    }
}
