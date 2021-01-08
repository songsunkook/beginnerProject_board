package util;

import domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.BoardService;
import service.CommentService;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class ExceptionChecker {

    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;

    //로그인 여부 확인 절차
    @Before("execution(* controller.BoardController.createArticle(..)) && args(httpSession)" +
            "execution(* controller.CommentController.createComment(..)) && args(httpSession)")
    public void checkLogin(HttpSession httpSession){
        User user = (User)httpSession.getAttribute("USER");
        if(user == null){
            //예외처리
        }
    }

    //본인 확인 절차 - 게시물
    @Before("execution(* controller.BoardController.updateArticle(..)) && args(articleId, httpSession)")
    public void checkSelfArticle(Long articleId, HttpSession httpSession){
        User tryUser = (User)httpSession.getAttribute("USER");
        if(tryUser.getId() != boardService.getArticleById(articleId).getUser_id()){
            //예외처리
        }
    }

    //본인 확인 절차 - 댓글
    @Before("execution(* controller.CommentController.updateComment(..)) && args(commentId, httpSession)")
    public void checkSelfComment(Long commentId, HttpSession httpSession){
        User tryUser = (User)httpSession.getAttribute("USER");
        if(tryUser.getId() != commentService.getCommentById(commentId).getUser_id()){
            //예외처리
        }
    }
}
