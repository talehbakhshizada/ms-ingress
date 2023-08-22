package az.company.msingress.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ElapsedTimeLogger {

    @Pointcut(value = "execution(* az.company.msingress.service.BookService.*(..))")
    public void elapsedTimePointCut(){}

    @SneakyThrows
    @Around(value = "elapsedTimePointCut()")
    public void elapsedTimeLogger(ProceedingJoinPoint jp){
        long startDate = System.currentTimeMillis();
        jp.proceed();
        long endDate = System.currentTimeMillis();
        log.info("Elapsed time : {}",endDate-startDate);
    }

    @Before(value = "elapsedTimePointCut()")
    public void logAllParameters(JoinPoint joinPoint){
        Arrays.stream(
                joinPoint.getArgs()).
                forEach(arg -> log.info("All method args : {}",arg));
    }
}
