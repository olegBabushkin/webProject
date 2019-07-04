package mvc.bookmanager.dao;

import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.Publisher;

import java.util.List;


public interface PublisherDao {
   /* public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);*/

    public Publisher getPublisherById(int id);

    public List<Publisher> listPublishers();
}
