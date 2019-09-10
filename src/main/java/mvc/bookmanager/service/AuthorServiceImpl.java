package mvc.bookmanager.service;

import mvc.bookmanager.dao.AuthorDao;
import mvc.bookmanager.dao.BookDao;
import mvc.bookmanager.dto.DTOSearch;
import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Book;
import mvc.bookmanager.repository.AuthorQuery;
import mvc.bookmanager.repository.BookQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;
    private BookDao bookDao;
    private AuthorQuery authorQuery;
    private BookQuery bookQuery;
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    @Qualifier(value = "hiberAuthor")
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    @Qualifier(value = "hiber")
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setAuthorQuery(AuthorQuery authorQuery) {
        this.authorQuery = authorQuery;
    }

    @Autowired
    public void setBookQuery(BookQuery bookQuery) {
        this.bookQuery = bookQuery;
    }

    @Override
    @Transactional
    public void addAuthor(Author author) throws AppException {
        logger.info("saveAuthor");
        if (this.authorQuery.getEqualsAuthor(author)) {
            this.authorDao.addAuthor(author);
        } else {
            logger.error("alreadyexist");
            throw new AppException("This author's title and authors already exist");
        }
    }


    @Override
    @Transactional
    public List<Author> findAuthor(String text) {
        List<Author> list = authorQuery.getAuthorByName(text);
        if (list.size() != 0) {
            return list;
        } else throw new AppException("Authors don't find");
    }

    @Transactional
    @Override
    public void editAuthor(Author author) throws AppException {
        List<Integer> bookListID = this.bookQuery.getBooksByOnlyAuthor(author);
        if (!bookListID.isEmpty()) {
            int count = 0;
            for (Integer id : bookListID) {
                for (Book book1 : author.getBooks()) {
                    if (id.equals(book1.getId())) {
                        count++;
                    }
                }
            }
        if (count == 0) throw new AppException("Find another books");
        }
        this.authorDao.updateAuthor(author);
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Transactional
    @Override
    public void removeAuthor(int id) {
        this.authorDao.removeAuthor(id);
    }

    @Transactional
    @Override
    public Author getAuthorById(int id) {
        return this.authorDao.getAuthorById(id);
    }

    @Transactional
    @Override
    public List<Author> listAuthors() {
        return this.authorDao.listAuthors();
    }
}
