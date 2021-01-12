package service;

import domain.Comment;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {
    boolean createComment(Comment comment, Long articleId, HttpSession httpSession);
    boolean updateComment(Comment comment, Long commentId, HttpSession httpSession);
    boolean deleteComment(Long commentId, HttpSession httpSession);
    List<Comment> getComments(Long articleId);
    Comment getCommentById(Long commentId);
}
