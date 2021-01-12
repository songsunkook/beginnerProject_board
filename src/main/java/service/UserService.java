package service;

import domain.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    boolean register(User user);
    boolean login(User user, HttpSession httpSession);
    boolean updateUser(User user);
    User getUserByAccountId(String accountId);
    User getUserById(Long id);
    void deleteUser();
}
