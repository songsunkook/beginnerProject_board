package service;

import domain.User;

public interface UserService {
    boolean register(User user);
    boolean login(User user);
    boolean updateUser(User user);
    User getUserByAccountId(String accountId);
    User getUserById(Long id);
}
