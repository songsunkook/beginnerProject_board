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
    public ResponseEntity<String> createArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board){
        if(boardService.createArticle(board))
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 불러옵니다.")
    public ResponseEntity<List<Board>> getList(@RequestParam(value = "page-number", required = false) Long pageNum){
        return new ResponseEntity<>(boardService.getList(pageNum), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/nickname", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 검색 (닉네임)", notes = "닉네임으로 게시글을 검색합니다.")
    public ResponseEntity<List<Board>> searchListByNickname(@RequestParam(value = "nickname", required = true) String nickname, @RequestParam(value = "page-number",required = false) Long pageNum){
        return new ResponseEntity<>(boardService.searchList(nickname, pageNum, 1), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/title", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 검색 (제목)", notes = "제목으로 게시글을 검색합니다.")
    public ResponseEntity<List<Board>> searchListByTitle(@RequestParam(value = "title", required = true) String title, @RequestParam(value = "page-number",required = false) Long pageNum){
        return new ResponseEntity<>(boardService.searchList(title, pageNum, 2), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 검색 (내용)", notes = "내용으로 게시글을 검색합니다.")
    public ResponseEntity<List<Board>> searchListByContent(@RequestParam(value = "content", required = true) String content, @RequestParam(value = "page-number",required = false) Long pageNum){
        return new ResponseEntity<>(boardService.searchList(content, pageNum, 3), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.PUT)
    @ApiOperation(value = "게시글 수정", notes = "등록된 게시물을 수정합니다.")
    public ResponseEntity<String> updateArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board, @PathVariable("article-id") Long articleId){
        if(boardService.updateArticle(board, articleId))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "게시글 삭제", notes = "등록된 게시물을 삭제합니다.")
    public ResponseEntity<String> deleteArticle(@PathVariable("article-id") Long articleId){
        if(boardService.softDeleteArticle(articleId))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}/like", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 좋아요", notes = "게시글에 좋아요 또는 좋아요 취소를 진행합니다.")
    public ResponseEntity<String> likeArticle(@PathVariable("article-id") Long articleId){
        try {
            if (boardService.likeArticle(articleId))
                return new ResponseEntity<>("like it !", HttpStatus.OK);
            else
                return new ResponseEntity<>("Unlike it !", HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
