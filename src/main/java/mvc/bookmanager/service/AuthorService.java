package mvc.bookmanager.service;

import mvc.bookmanager.dto.DTOSearch;
import mvc.bookmanager.exeption.AppException;
import mvc.bookmanager.model.Author;


import java.util.List;

public interface AuthorService {
    public void addAuthor(Author author) throws AppException;

    public void editAuthor(Author author) throws AppException;

    public void updateAuthor(Author author);

    public void removeAuthor(int id);

    public Author getAuthorById(int id);

    public List<Author> listAuthors();
    List<Author> findAuthor(String text);
/*
    public List<Book> findBook(DTOSearch search);

    public void findBookByAuthor(String name);*/
}
