package service;

import domain.Board;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    boolean createArticle(Board board, HttpSession httpSession);
    boolean updateArticle(Board board, Long articleId, HttpSession httpSession);
    boolean likeArticle(Long articleId, HttpSession httpSession) throws Exception;
    Boolean softDeleteArticle(Long articleId, HttpSession httpSession);
    Board getArticleById(Long articleId);
    Board readArticle(Long articleId);
    List<Board> getList(Long pageNum);
}
