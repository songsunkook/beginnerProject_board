package service;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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
        comment.setArticle_id(articleId);
        comment.setUser_id(userId);
        return commentMapper.createComment(comment) == 1;
    }

    @Override
    public boolean updateComment(Comment comment, Long commentId, HttpSession httpSession) {
        Long tryUserId = (Long)httpSession.getAttribute("userId");
        Comment dbComment = getCommentById(commentId);
        if(tryUserId == dbComment.getUser_id()
                && dbComment.getDeleted_at() == null){
            comment.setId(dbComment.getId());
            return commentMapper.updateComment(comment) == 1;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Long commentId, HttpSession httpSession) {
        Comment dbComment = getCommentById(commentId);
        Long tryUserId = (Long)httpSession.getAttribute("userId");
        if(tryUserId == dbComment.getUser_id()){
            return commentMapper.deleteComment(dbComment) == 1;
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
