package com.sqc95111.javaknownledge;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect1 {

//    @Before(value = "")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("[Aspect1] before advise");
//    }

    @Around(value = "com.sqc95111.javaknownledge.AspectAopClass.aspectMethod()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Aspect1] around advise 1");
        pjp.proceed();
        System.out.println("[Aspect1] around advise2");
    }

    @AfterReturning(value = "com.sqc95111.javaknownledge.AspectAopClass.aspectMethod()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    @AfterThrowing(value = "com.sqc95111.javaknownledge.AspectAopClass.aspectMethod()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After(value = "com.sqc95111.javaknownledge.AspectAopClass.aspectMethod()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }
}
