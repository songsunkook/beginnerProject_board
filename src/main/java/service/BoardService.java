package service;

import domain.Board;
import domain.User;

import java.util.List;

public interface BoardService {
    boolean createArticle(Board board);
    Board getArticleById(Long articleId);
    Board readArticle(Long articleId);
    List<Board> getList();
    boolean updateArticle(Board board);
}
