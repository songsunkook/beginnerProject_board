package repository;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    int createArticle(Board board);
    Board getArticleById(Long articleId);
    List<Board> getList();
    int updateArticle(Board board);
    int softDeleteArticle(Board board);
    void increaseViews(Board board);
    void increaseLikes(Board board);
    void decreaseLikes(Board board);
}
