package service;

import domain.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import util.BcryptUtil;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    BcryptUtil bcryptUtil;

    @Override
    public boolean register(User user) {
        if(userMapper.getUserByAccountId(user.getAccount_id()) == null){
            user.setPassword( bcryptUtil.Encrypt(user.getPassword()) );
            return userMapper.createUser(user) == 1;
        }
        else
            return false;
    }

    @Override
    public boolean login(User user) {
        if(userMapper.getUserByAccountId(user.getAccount_id()) != null){
            return bcryptUtil.checkPassword(user.getPassword(), userMapper.getUserByAccountId(user.getAccount_id()).getPassword());
        }
        else
            return false;
    }

    @Override
    public boolean updateUser(User user) {
        user.setPassword( bcryptUtil.Encrypt(user.getPassword()) );
        user.setId(userMapper.getUserByAccountId(user.getAccount_id()).getId());
        return userMapper.updateUser(user) == 1;
    }

    @Override
    public User getUserByAccountId(String accountId) {
        return userMapper.getUserByAccountId(accountId);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
