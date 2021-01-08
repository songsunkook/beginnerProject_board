package service;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CommentMapper;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean createComment(Comment comment) {
        return commentMapper.createComment(comment) == 1;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentMapper.updateComment(comment) == 1;
    }

    @Override
    public List<Comment> getComments(Long articleId) {
        return commentMapper.getComments(articleId);
    }
}
