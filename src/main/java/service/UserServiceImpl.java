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
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
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
    public boolean softDeleteUser(User user, HttpSession httpSession) {
        Long userId = (Long)httpSession.getAttribute("userId");
        User dbUser = getUserByAccountId(user.getAccount_id());
        //1번째 인자 : NullPointerException 방지
        //2번째 인자 : 로그인된 id와 입력받은 accountId의 DB 상 id가 일치하는지 (로그인된 사람 본인인지)
        //3번째 인자 : accountId 와 password 가 match 되는지 (아이디 비밀번호 체크)
        if( (userId != null) && (userId == dbUser.getId())  && login(user, httpSession)){
            if(userMapper.softDeleteUser(dbUser) == 1){
                httpSession.removeAttribute("userId");
                return true;
            }
        }
        return false;
    }

    @Override
    public void hardDeleteUser(){
        //탈퇴한 지 30일이 지난 유저 데이터는 자동으로 삭제
        //삭제 주기 : 매일 자정 (자세한 설정은 dispatcher-servlet.xml)
        Date now = new Date();
        List<User> deletedUsers = userMapper.getDeletedUsers();
        for(int i = 0; i < deletedUsers.size(); i++){
            Date checkTime = new Date(deletedUsers.get(i).getDeleted_at().getTime());
            Long diffDay = ( now.getTime() - checkTime.getTime() ) / (24*60*60*1000);
            if(diffDay >= 30)
                userMapper.hardDeleteUser(deletedUsers.get(i));
        }
    }
}
