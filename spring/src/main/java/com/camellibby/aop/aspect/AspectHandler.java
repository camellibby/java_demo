package com.camellibby.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class AspectHandler {

    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---before---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
    }

    public void after(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---after---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("~~~aop---afterReturning---method[" + signature.getDeclaringTypeName() + "#" + signature.getName() + "]");
        System.out.println("~~~aop---afterReturning---result[" + result + "]");
    }

    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("~~~aop---afterThrowing");
        System.out.println("~~~aop---afterThrowing---exception[" + e.getMessage() + "]");
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("~~~aop---around---before");
        Object result = joinPoint.proceed();
        System.out.println("~~~aop---around---result[" + result + "]");
        System.out.println("~~~aop---around---after");
        return result;
    }
}
