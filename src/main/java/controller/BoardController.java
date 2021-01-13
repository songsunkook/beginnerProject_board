package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import domain.Board;
import domain.Like;
import domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    public ResponseEntity<String> createArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board, HttpSession httpSession){
        if(boardService.createArticle(board, httpSession))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 읽기", notes = "게시글을 읽습니다.")
    public ResponseEntity<Board> readArticle(@PathVariable("article-id") Long articleId){
        return new ResponseEntity<>(boardService.readArticle(articleId), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{page-number}", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 불러옵니다.")
    public ResponseEntity<List<Board>> getList(@PathVariable("page-number") Long pageNum){
        return new ResponseEntity<>(boardService.getList(pageNum), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.PUT)
    @ApiOperation(value = "게시글 수정", notes = "등록된 게시물을 수정합니다.")
    public ResponseEntity<String> updateArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board, @PathVariable("article-id") Long articleId, HttpSession httpSession){
        if(boardService.updateArticle(board, articleId, httpSession))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "게시글 삭제", notes = "등록된 게시물을 삭제합니다.")
    public ResponseEntity<String> deleteArticle(@PathVariable("article-id") Long articleId, HttpSession httpSession){
        if(boardService.softDeleteArticle(articleId, httpSession))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}/like", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 좋아요", notes = "게시글에 좋아요 또는 좋아요 취소를 진행합니다.")
    public ResponseEntity<String> likeArticle(@PathVariable("article-id") Long articleId, HttpSession httpSession){
        try {
            if (boardService.likeArticle(articleId, httpSession))
                return new ResponseEntity<>("like it !", HttpStatus.OK);
            else
                return new ResponseEntity<>("Unlike it !", HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
