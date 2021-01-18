package service;

import domain.Board;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    boolean createArticle(Board board);
    boolean updateArticle(Board board, Long articleId);
    List<Board> searchList(String value, Long pageNum, int type);
    boolean likeArticle(Long articleId) throws Exception;
    boolean softDeleteArticle(Long articleId);
    Board getArticleById(Long articleId);
    Board readArticle(Long articleId);
    List<Board> getList(Long pageNum);
}
