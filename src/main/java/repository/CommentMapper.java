package repository;

import domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int createComment(Comment comment);
    int updateComment(Comment comment);
    int deleteComment(Comment comment);
    List<Comment> getComments(Long articleId);
    Comment getCommentById(Long commentId);
}
