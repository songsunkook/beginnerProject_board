package controller;

import domain.Board;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    public ResponseEntity<String> write(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board){
        //아직 로그인을 구현 못했기 때문에 user_id는 수동으로 넣어줘야 함

        if(boardService.createArticle(board))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 읽기", notes = "게시글을 읽습니다.")
    public ResponseEntity<Board> read(@PathVariable("article-id") Long articleId){
        return new ResponseEntity<>(boardService.readArticle(articleId), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 불러옵니다.")
    public ResponseEntity<List<Board>> getList(){
        return new ResponseEntity<>(boardService.getList(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 수정", notes = "등록된 게시물을 수정합니다.")
    public ResponseEntity<String> updateArticle(@RequestBody Board board, @PathVariable("article-id") Long articleId){
        board.setId(articleId);
        if(boardService.updateArticle(board))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
