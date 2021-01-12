package service;

import domain.Board;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.BoardMapper;
import repository.UserMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper boardMapper;

    @Override
    public boolean createArticle(Board board, HttpSession httpSession) {
        Long userId = (Long)httpSession.getAttribute("userId");
        if(userId != null){
            board.setUser_id(userId);
            return boardMapper.createArticle(board) == 1;
        }
        return false;
    }

    @Override
    public boolean updateArticle(Board board, Long articleId, HttpSession httpSession) {
        Long tryUserId = (Long)httpSession.getAttribute("userId");
        Board dbBoard = getArticleById(articleId);
        if(dbBoard != null
                && tryUserId == dbBoard.getUser_id()
                && dbBoard.getDeleted_at() == null){
            board.setId(articleId);
            return boardMapper.updateArticle(board) == 1;
        }
        return false;
    }

    @Override
    public Boolean softDeleteArticle(Long articleId, HttpSession httpSession) {
        Long userId = (Long)httpSession.getAttribute("userId");
        Board dbBoard = getArticleById(articleId);
        if(userId == dbBoard.getUser_id()
                && dbBoard.getDeleted_at() == null){
            return boardMapper.softDeleteArticle(dbBoard) == 1;
        }
        return false;
    }


    @Override
    public Board getArticleById(Long articleId) {
        return boardMapper.getArticleById(articleId);
    }

    @Override
    public Board readArticle(Long articleId) {
        Board board = getArticleById(articleId);
        if(board.getDeleted_at() == null){
            boardMapper.increaseViews(board);
            return board;
        }
        else
            return null;
    }

    @Override
    public List<Board> getList() {
        return boardMapper.getList();
    }

}
