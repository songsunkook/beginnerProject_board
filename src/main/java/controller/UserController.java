package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "회원가입", notes = "전달받은 정보를 기반으로 회원가입을 진행합니다.")
    public ResponseEntity<String> register(@RequestBody User user){
        //User temp = userService.register(user);
        //return new ResponseEntity<>(, HttpStatus.OK);
        if(userService.register(user))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ApiOperation(value = "회원 정보 수정", notes = "account_id를 기반으로 닉네임과 비밀번호를 변경합니다.")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        //비밀번호 확인 절차 필요

        if(userService.updateUser(user))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
