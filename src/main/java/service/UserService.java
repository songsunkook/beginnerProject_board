package service;

import domain.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    boolean register(User user);
    boolean login(User user);
    void logout();
    boolean rejoin(User user);
    boolean updateUser(User user);
    User getUserByAccountId(String accountId);
    boolean softDeleteUser(User user);
    void hardDeleteUser();
}
