package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "회원가입", notes = "전달받은 정보를 기반으로 회원가입을 진행합니다.")
    public ResponseEntity<String> register(@ApiParam(value = "(required: account_id, password, nickname)", required = true) @RequestBody User user){
        if(userService.register(user))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "전달받은 정보를 기반으로 로그인을 진행합니다.")
    public ResponseEntity<String> login(@ApiParam(value = "(required: account_id, password)", required = true) @RequestBody User user, HttpSession httpSession){
        if(userService.login(user)){
            httpSession.setAttribute("USER", userService.getUserByAccountId(user.getAccount_id()));
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ApiOperation(value = "로그아웃", notes = "세션을 제거하여 로그아웃합니다.")
    public void logout(HttpSession httpSession){
        httpSession.removeAttribute("USER");
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "회원 정보 수정", notes = "account_id를 기반으로 닉네임과 비밀번호를 변경합니다.")
    public ResponseEntity<String> updateUser(@ApiParam(value = "(required: account_id, password, nickname)", required = true) @RequestBody User user){
        //비밀번호 확인 절차 필요 ( 현재 비밀번호를 확인 후 여기로 진입해야 함, 어떻게? )
        if(userService.updateUser(user))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}