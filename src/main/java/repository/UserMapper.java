package repository;

import domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// Mapper : SQL문을 호출하기 위한 인터페이스
@Repository
public interface UserMapper {
    int createUser(User user);
    int updateUser(User user);
    User getUserByAccountId(String accountId);
    User getUserById(Long id);

}
