package mvc.bookmanager.dao;

import mvc.bookmanager.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookDao {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks();
}
