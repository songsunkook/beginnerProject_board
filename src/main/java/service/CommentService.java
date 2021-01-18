package service;

import domain.Comment;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {
    boolean createComment(Comment comment, Long articleId);
    boolean updateComment(Comment comment, Long commentId);
    boolean deleteComment(Long commentId);
    List<Comment> getComments(Long articleId);
    Comment getCommentById(Long commentId);
}
