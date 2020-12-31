package service;

import domain.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    String getTime();

    boolean register(User user);
}
