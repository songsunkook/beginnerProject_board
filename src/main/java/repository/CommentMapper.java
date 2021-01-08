package repository;

import domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int createComment(Comment comment);
    int updateComment(Comment comment);
    List<Comment> getComments(Long articleId);
}
