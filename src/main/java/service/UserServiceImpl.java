package service;

import domain.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean register(User user) {
        //return userMapper.checkUniqueUser(user);
        if(userMapper.getUserByAccountId(user) == null)
            return userMapper.createUser(user) == 1;
        else
            return false;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) == 1;
    }
}
