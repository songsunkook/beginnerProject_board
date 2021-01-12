package service;

import domain.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import util.BcryptUtil;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
    public boolean login(User user, HttpSession httpSession) {
        User loginUser = getUserByAccountId(user.getAccount_id());

        if(loginUser != null && bcryptUtil.checkPassword(user.getPassword(), loginUser.getPassword())){
            httpSession.setAttribute("userId", loginUser.getId());
            return true;
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

    @Override
    public void deleteUser(){
        //탈퇴한 지 30일이 지난 유저 데이터는 자동으로 삭제
        //삭제 주기 : 매일 자정 (자세한 설정은 dispatcher-servlet.xml)
        Date now = new Date();
        List<User> deletedUsers = userMapper.getDeletedUsers();
        for(int i = 0; i < deletedUsers.size(); i++){
            Date checkTime = new Date(deletedUsers.get(i).getDeleted_at().getTime());
            Long diffDay = ( now.getTime() - checkTime.getTime() ) / (24*60*60*1000);
            if(diffDay >= 30)
                userMapper.deleteUser(deletedUsers.get(i));
        }
    }
}
