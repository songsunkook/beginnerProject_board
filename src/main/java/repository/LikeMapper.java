package repository;

import domain.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
    Like getLike(@Param("articleId") Long articleId, @Param("userId") Long userId);
    int setLike(@Param("articleId") Long articleId, @Param("userId") Long userId);
    int setUnLike(Like like);
}
