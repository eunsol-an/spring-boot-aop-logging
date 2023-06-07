package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // controller 패키지에서 *Controller 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    // service 패키지에서 *Service 클래스에 있는 메서드 중 파라미터 0개인 메서드만
    @Around("execution(* com.example.aop.controller.*Controller.*()) || execution(* com.example.aop..service.*Service.*())")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info(">>> This - '" + joinPoint.getThis() + "'"); // Advice 를 행하는 객체
        logger.info(">>> Kind - '" + joinPoint.getKind() + "'"); // 해당 Advice 의 타입
        logger.info(">>> Target - '" + joinPoint.getTarget() + "'"); // Target 객체

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();
        // getSignature() : 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옴

        if (name.contains("Controller")) {
            type = ">>> Controller - '";

        } else if (name.contains("Service")) {
            type = ">>> Service - '";
        }

        logger.info(type + name + "." + joinPoint.getSignature().getName() + "()'");
        // getName - 메서드 이름
        return joinPoint.proceed();
    }
}
