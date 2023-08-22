package az.company.msingress.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* az.company.msingress.service.BookService.*(..))")
    public void logPointcut() {}

    @SneakyThrows
    @Around("logPointcut()")
    public void logBefore(ProceedingJoinPoint joinPoint) {
        log.info("Before method execution: {}", joinPoint.getSignature().getName());
        joinPoint.proceed();
        log.info("After method execution: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("METHOD: {} , RESPONSE: {}",joinPoint.getSignature().getName(),result);
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint ,Throwable ex) {
        log.error("Exception occurred in method: {}" , joinPoint.getSignature().getName() + ", Exception: " + ex);
        log.info("After method execution: {}", joinPoint.getSignature().getName());
    }
}
