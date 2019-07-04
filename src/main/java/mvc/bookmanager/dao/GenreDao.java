package mvc.bookmanager.dao;

import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.Genre;

import java.util.List;


public interface GenreDao {
   /* public void addBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);*/

    public Genre getGenreById(int id);

    public List<Genre> listGenres();
}
