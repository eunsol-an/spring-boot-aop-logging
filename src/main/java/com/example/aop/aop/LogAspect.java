package com.example.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;



@Slf4j
@Aspect
@Component
public class LogAspect {

    //BookService의 모든 메서드
    @Around("execution(* com.example.aop.service.BookService.*(..))")
//    @Around("execution(* com.example.aop.controller..*.*(..))")
//    @Around("execution(* com.example.aop..*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        log.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        return result;
    }

    // com.example.aop.controller 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    // Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) log.info("no parameter");
        for (Object arg : args) {
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }
    }

    // Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        log.info("return type = {}", returnObj.getClass().getSimpleName());
        log.info("return value = {}", returnObj);
    }

    // Pointcut에 의해 필터링된 경로로 들어오는 메서드 예외 발생 이후 적용
    @AfterThrowing(value = "cut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) throws RuntimeException {
        // 메서드 정보 받아오기
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        log.info("After Throwing exception in method:" + joinPoint.getSignature());
        log.info("Exception is:" + exception.getMessage());
    }

    // JoinPoint로 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }


    // controller 패키지에서 *Controller 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    // service 패키지에서 *Service 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    @Around("execution(* com.example.aop.controller.*Controller.*()) || execution(* com.example.aop..service.*Service.*())")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(">>> This - '" + joinPoint.getThis() + "'"); // Advice 를 행하는 객체
        log.info(">>> Kind - '" + joinPoint.getKind() + "'"); // 해당 Advice 의 타입
        log.info(">>> Target - '" + joinPoint.getTarget() + "'"); // Target 객체

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();
        // getSignature() : 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옴

        if (name.contains("Controller")) {
            type = ">>> Controller - '";

        } else if (name.contains("Service")) {
            type = ">>> Service - '";
        }

        log.info(type + name + "." + joinPoint.getSignature().getName() + "()'");
        // getName - 메서드 이름
        return joinPoint.proceed();
    }
}

