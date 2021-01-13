package repository;

import domain.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    int createArticle(Board board);
    Board getArticleById(Long articleId);
    List<Board> getList(Long minArticle);
    List<Board> searchList(@Param("column") String column, @Param("minArticle") Long minArticle, @Param("value") String value);
    Long getArticleCount();
    Long getArticleCountByColumn(@Param("column") String column, @Param("value") String value);
    int updateArticle(Board board);
    int softDeleteArticle(Board board);
    void increaseViews(Board board);
    void increaseLikes(Board board);
    void decreaseLikes(Board board);
}
