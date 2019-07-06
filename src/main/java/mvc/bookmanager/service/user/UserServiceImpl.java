package mvc.bookmanager.service.user;

import mvc.bookmanager.dao.RoleDao;
import mvc.bookmanager.dao.UserDao;
import mvc.bookmanager.model.Role;
import mvc.bookmanager.model.User;
import mvc.bookmanager.repository.RoleQuery;
import mvc.bookmanager.repository.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserQuery userQuery;

    @Autowired
    private RoleQuery roleQuery;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(1L));
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userQuery.getUserByName(username);
    }
}
