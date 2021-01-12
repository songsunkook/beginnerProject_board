package service;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.CommentMapper;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean createComment(Comment comment, Long articleId, HttpSession httpSession) {
        Long userId = (Long)httpSession.getAttribute("userId");
        if(userId != null){
            comment.setArticle_id(articleId);
            comment.setUser_id(userId);
            return commentMapper.createComment(comment) == 1;
        }
        return false;
    }

    @Override
    public boolean updateComment(Comment comment, Long commentId, HttpSession httpSession) {
        Long tryUserId = (Long)httpSession.getAttribute("userId");
        Comment dbComment = getCommentById(commentId);

        if(tryUserId == dbComment.getUser_id()){
            comment.setId(dbComment.getId());
            return commentMapper.updateComment(comment) == 1;
        }
        return false;
    }

    @Override
    public List<Comment> getComments(Long articleId) {
        return commentMapper.getComments(articleId);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.getCommentById(commentId);
    }
}
