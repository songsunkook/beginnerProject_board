package repository;

import domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// Mapper : SQL문을 호출하기 위한 인터페이스
@Repository
public interface UserMapper {
    //어노테이션 방식
    @Select(value = "select * from user")
    List<User> getUserList();

    //xml 방식
    String getTime();


    int createUser(User user);
}
