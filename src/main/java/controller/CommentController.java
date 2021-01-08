package controller;

import domain.Comment;
import domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/board/{article-id}/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성합니다.")
    public ResponseEntity<String> createComment(@ApiParam(value = "(required: content)", required = true) @RequestBody Comment comment, @PathVariable("article-id") Long articleId, HttpSession httpSession){
        //로그인 여부 확인 절차 필요

        User user = (User)httpSession.getAttribute("USER");
        if(user != null){
            comment.setArticle_id(articleId);
            comment.setUser_id(user.getId());
            if(commentService.createComment(comment))
                return new ResponseEntity<>("success", HttpStatus.OK);
        }
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{comment-id}", method = RequestMethod.POST)
    @ApiOperation(value = "댓글 수정", notes = "작성된 댓글을 수정합니다.")
    public ResponseEntity<String> updateComment(@ApiParam(value = "(required: content)", required = true) @RequestBody Comment comment, @PathVariable("article-id") Long articleId, @PathVariable("comment-id") Long commentId){
        //본인 확인 절차 필요

        comment.setArticle_id(articleId);
        comment.setId(commentId);
        if(commentService.updateComment(comment))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "댓글 조회", notes = "특정 게시글의 댓글을 조회합니다.")
    public ResponseEntity<List<Comment>> updateComment(@PathVariable("article-id") Long articleId){
        return new ResponseEntity<>(commentService.getComments(articleId), HttpStatus.OK);
    }
}