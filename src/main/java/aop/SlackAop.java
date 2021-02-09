package aop;

import domain.SlackNotiAttachment;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.SlackNotiSender;

import java.util.Arrays;

@Component
@Aspect
public class SlackAop {
    @Autowired
    SlackNotiSender slackNotiSender;

    @Around("bean(*)")//AOP 테스트용
    public void sendSlackOnReturning2(ProceedingJoinPoint joinPoint) throws Throwable{
        String service = joinPoint.getClass().getName();
        String method = joinPoint.getSignature().getName();

        SlackNotiAttachment attachment = new SlackNotiAttachment();
        attachment.setColor("good");
        attachment.setTitle(String.format("%s.%s", service, method));
        slackNotiSender.sendNotice(attachment);

        joinPoint.proceed();
    }

    //@AfterReturning("execution(* controller.*Controller.*(..))")//AOP 테스트용
    @Before("execution(* controller.UserController.login(..))")//AOP 테스트용
    public void sendSlackOnReturning(JoinPoint joinPoint){
        String service = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();

        SlackNotiAttachment attachment = new SlackNotiAttachment();
        attachment.setColor("good");
        attachment.setTitle(String.format("%s.%s", service, method));
        slackNotiSender.sendNotice(attachment);
    }

    @Before("execution(* *(..))")//AOP 테스트용
    public void sendSlackOnReturning3(JoinPoint joinPoint){
        SlackNotiAttachment attachment = new SlackNotiAttachment();
        attachment.setColor("danger");
        attachment.setTitle("제발 돼주세요");
        slackNotiSender.sendNotice(attachment);
    }

    @AfterThrowing(pointcut = "execution(* *(..))", throwing = "exception")
    public void sendSlackOnCrash(JoinPoint joinPoint, Throwable exception){
        String errorMessage = exception.getMessage();
        String errorService = joinPoint.getTarget().getClass().getName();
        String errorMethod = joinPoint.getSignature().getName();
        String errorArgs = Arrays.toString(joinPoint.getArgs());
        int errorLine = exception.getStackTrace()[0].getLineNumber();
        String errorFile = exception.getStackTrace()[0].getFileName();
        String errorName = exception.getClass().getSimpleName();

        String message = String.format("%s %s Line %d\n```%s\n\n===== [Arguments] =====\n%s```", errorName, errorFile, errorLine, errorMessage, errorArgs);

        SlackNotiAttachment attachment = new SlackNotiAttachment();
        attachment.setColor("danger");
        attachment.setTitle(String.format("%s.%s", errorService, errorMethod));
        attachment.setText(message);

        //아이콘이랑 봇명 바꾸기
        slackNotiSender.sendNotice(attachment);
    }
}
