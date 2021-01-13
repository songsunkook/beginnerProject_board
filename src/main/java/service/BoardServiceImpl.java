package service;

import domain.Board;
import domain.Like;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.BoardMapper;
import repository.LikeMapper;
import repository.UserMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    LikeMapper likeMapper;

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
    public List<Board> searchList(String value, Long pageNum, int type) {
        Long maxPage;
        String column;
        switch (type){
            case 1://닉네임
                column = "user.nickname"; break;
            case 2://제목
                column = "board.title"; break;
            case 3://내용
                column = "board.content"; break;
            default:
                return null;
        }
        maxPage = ( boardMapper.getArticleCountByColumn(column, value) / 10 ) + 1;
        if(pageNum <= 0)
            pageNum = 1L;
        if(pageNum > maxPage)
            pageNum = maxPage;
        return boardMapper.searchList(column, (pageNum-1)*10, value);
    }

    @Override
    public boolean likeArticle(Long articleId, HttpSession httpSession) throws Exception {
        Long userId = (Long)httpSession.getAttribute("userId");
        Like like = likeMapper.getLike(articleId, userId);
        Board board = getArticleById(articleId);
        if(board != null) {
            //아직 좋아요를 안눌렀다면
            if (like == null) {
                boardMapper.increaseLikes(board);
                likeMapper.setLike(articleId, userId);
                return true;
            }
            //좋아요를 이미 눌렀다면
            else {
                boardMapper.decreaseLikes(board);
                likeMapper.setUnLike(like);
                return false;
            }
        }
        else
            throw new Exception("This article does not exist.");
    }

    @Override
    public boolean softDeleteArticle(Long articleId, HttpSession httpSession) {
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
    public List<Board> getList(Long pageNum) {
        Long maxPage = ( boardMapper.getArticleCount() / 10 ) + 1;
        if(pageNum <= 0)
            pageNum = 1L;
        if(pageNum > maxPage)
            pageNum = maxPage;
        return boardMapper.getList(( pageNum - 1 ) * 10);
    }

}
