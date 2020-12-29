package repository;

import domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select(value = "select * from user")
    public List<User> getUserList();
}
