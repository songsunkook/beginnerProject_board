package service;

import domain.Comment;

import java.util.List;

public interface CommentService {
    boolean createComment(Comment comment);
    boolean updateComment(Comment comment);
    List<Comment> getComments(Long articleId);
    Comment getCommentById(Long commentId);
}
