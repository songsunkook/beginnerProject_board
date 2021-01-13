package service;

import domain.Board;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    boolean createArticle(Board board, HttpSession httpSession);
    boolean updateArticle(Board board, Long articleId, HttpSession httpSession);
    List<Board> searchList(String value, Long pageNum, int type);
    boolean likeArticle(Long articleId, HttpSession httpSession) throws Exception;
    boolean softDeleteArticle(Long articleId, HttpSession httpSession);
    Board getArticleById(Long articleId);
    Board readArticle(Long articleId);
    List<Board> getList(Long pageNum);
}
