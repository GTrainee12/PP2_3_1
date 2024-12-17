package web.service;


import web.model.User;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();

    void saveUser(User user);

    Optional<User> findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}
