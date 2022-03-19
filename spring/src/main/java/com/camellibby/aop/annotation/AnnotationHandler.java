package com.camellibby.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationHandler {
    @Pointcut("execution(* com.camellibby.aop.annotation.Robot.hello(..))")
    public void myPointCut() {
    }

    @Before("myPointCut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---before---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
    }

    @After("myPointCut()")
    public void after(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---after---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
    }

    @AfterReturning(value = "myPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---afterReturning---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
        System.out.println("~~~aop---afterReturning---result[" + result + "]");
    }

    @AfterThrowing(value = "myPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---afterReturning---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
        System.out.println("~~~aop---afterThrowing---exception[" + e.getMessage() + "]");
    }

    @Around(value = "myPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("~~~aop---around---before");
        Object result = joinPoint.proceed();
        System.out.println("~~~aop---around---result[" + result + "]");
        System.out.println("~~~aop---around---after");
        return result;
    }
}
