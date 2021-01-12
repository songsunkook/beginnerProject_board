package service;

import domain.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    boolean register(User user);
    boolean login(User user, HttpSession httpSession);
    void logout(HttpSession httpSession);
    boolean rejoin(User user);
    boolean updateUser(User user);
    User getUserByAccountId(String accountId);
    User getUserById(Long id);
    boolean softDeleteUser(User user, HttpSession httpSession);
    void hardDeleteUser();
}
