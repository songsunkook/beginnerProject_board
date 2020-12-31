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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String root() throws JsonProcessingException{
        System.out.println("루트");
        System.out.println(new ObjectMapper().writeValueAsString(userService.getUserList()));

        return "home";
    }

    @RequestMapping(value = "/gettime", method = RequestMethod.GET)
    public String test() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(userService.getTime()));
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST, consumes = "application/json")
    @ApiOperation(value = "회원가입", notes = "전달받은 정보를 기반으로 회원가입을 진행합니다.")
    public ResponseEntity<String> register(@RequestBody User user){
        if(userService.register(user))
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
