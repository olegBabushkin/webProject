package mvc.bookmanager.service.user;

import mvc.bookmanager.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
