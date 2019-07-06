package mvc.bookmanager.dao;

import mvc.bookmanager.model.Book;
import mvc.bookmanager.model.User;

import java.util.List;


public interface UserDao {
    public void addUser(User user);

    public void removeBook(int id);

    public User getUserById(int id);

    public List<User> listUsers();
}
