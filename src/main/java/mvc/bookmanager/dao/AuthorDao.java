package mvc.bookmanager.dao;

import mvc.bookmanager.model.Author;


import java.util.List;


public interface AuthorDao {
    public void addAuthor(Author author);

    public void updateAuthor(Author book);

    public void removeAuthor(int id);

    public Author getAuthorById(int id);

    public List<Author> listAuthors();
}
