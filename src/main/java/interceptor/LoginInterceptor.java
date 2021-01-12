package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession httpSession = request.getSession();
        Long userId = (Long)httpSession.getAttribute("userId");

        if(userId == null){
            PrintWriter out = response.getWriter();
            out.println("You are not logged in");
            return false;
        }
        else{
            return true;
        }
    }
}
