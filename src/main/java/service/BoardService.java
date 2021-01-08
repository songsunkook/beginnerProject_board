package service;

import domain.Board;
import domain.User;

import java.util.List;

public interface BoardService {
    boolean createArticle(Board board);
    Board readArticle(Long articleId);
    List<Board> getList();
    boolean updateArticle(Board board);
}
