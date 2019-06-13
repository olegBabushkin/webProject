package mvc.bookmanager.service;

import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    public void addBook(Book book) throws AppException;

    public void editBook(Book book) throws AppException;

    public void updateBook(Book book);

    public void removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks();

    public List<Book> findBook(String choice, String text);

    public void findBookByAuthor(String name);
}
