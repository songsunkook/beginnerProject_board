package service;

import domain.Board;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BoardMapper;
import repository.UserMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper boardMapper;

    @Override
    public boolean createArticle(Board board) {
        //user_id 설정
        //board.setUser_id();

        return boardMapper.createArticle(board) == 1;
    }

    @Override
    public Board readArticle(Long articleId) {
        return boardMapper.getArticleById(articleId);
    }

    @Override
    public List<Board> getList() {
        return boardMapper.getList();
    }

    @Override
    public boolean updateArticle(Board board) {
        return boardMapper.updateArticle(board) == 1;
    }


}
