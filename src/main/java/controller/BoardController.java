package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import domain.Board;
import domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BoardService;
import service.UserService;
import springfox.documentation.spring.web.json.Json;
import util.ConfirmUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ConfirmUtil confirmUtil;
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    public ResponseEntity<String> createArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board, HttpSession httpSession){
        //로그인 여부 확인 절차 필요
        if(!confirmUtil.checkLogin(httpSession))
            return new ResponseEntity<>("fail (not login)", HttpStatus.INTERNAL_SERVER_ERROR);

        User user = (User)httpSession.getAttribute("USER");
        if(user != null){
            board.setUser_id(user.getId());
            if(boardService.createArticle(board))
                return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 읽기", notes = "게시글을 읽습니다.")
    public ResponseEntity<Board> readArticle(@PathVariable("article-id") Long articleId){
        //닉네임을 Board와 함께 반환할 방법 ?? 굳이 함께 반환해야되나
        return new ResponseEntity<>(boardService.readArticle(articleId), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 불러옵니다.")
    public ResponseEntity<List<Board>> getList(){
        return new ResponseEntity<>(boardService.getList(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{article-id}", method = RequestMethod.PUT)
    @ApiOperation(value = "게시글 수정", notes = "등록된 게시물을 수정합니다.")
    public ResponseEntity<String> updateArticle(@ApiParam(value = "(required: title, content)", required = true) @RequestBody Board board, @PathVariable("article-id") Long articleId, HttpSession httpSession){
        //본인 확인 절차 필요
        if(!confirmUtil.checkSelfBoard(articleId, httpSession))
            return new ResponseEntity<>("fail (not self)", HttpStatus.INTERNAL_SERVER_ERROR);

        board.setId(articleId);
        if(boardService.updateArticle(board))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
