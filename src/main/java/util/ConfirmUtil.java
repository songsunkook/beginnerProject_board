package util;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.BoardService;
import service.CommentService;
import service.UserService;

import javax.servlet.http.HttpSession;

@Component
public class ConfirmUtil {
    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;

    public boolean checkLogin(HttpSession httpSession){
        User user = (User)httpSession.getAttribute("USER");
        if(user == null)
            return false;
        else
            return true;

    }

    public boolean checkSelfBoard(Long articleId, HttpSession httpSession){
        User tryUser = (User)httpSession.getAttribute("USER");
        if(tryUser == null || tryUser.getId() != boardService.getArticleById(articleId).getUser_id()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkSelfComment(Long commentId, HttpSession httpSession){
        User tryUser = (User)httpSession.getAttribute("USER");
        if(tryUser == null || tryUser.getId() != commentService.getCommentById(commentId).getUser_id()){
            return false;
        }
        else{
            return true;
        }
    }

}
