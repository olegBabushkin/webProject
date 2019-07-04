package mvc.bookmanager.service;

import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Author;
import mvc.bookmanager.model.Genre;

import java.util.List;

public interface GenreService {
    public void addGenre(Genre genre) throws AppException;

    public void editGenre(Genre genre) throws AppException;

    public void updateGenre(Genre genre);

    public void removeGenre(int id);

    public Genre getGenreById(int id);

    public List<Genre> listGenres();
/*
    public List<Book> findBook(DTOSearch search);

    public void findBookByAuthor(String name);*/
}
