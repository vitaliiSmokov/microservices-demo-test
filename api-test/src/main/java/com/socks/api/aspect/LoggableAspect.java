package com.socks.api.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
public class LoggableAspect {

    @Pointcut("execution(* com.socks.api.services.*.*(..))")
    public void anyMethodInServices() {
        //pointcut body, should be empty
    }

    @Pointcut("execution(* com.socks.api.assertions*.*(..))")
    public void anyMethodInAssertion() {
        //pointcut body, should be empty
    }

    @Before("anyMethodInServices() || anyMethodInAssertion()")
    public void step(JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String name = joinPoint.getArgs().length > 0
                ? String.format("%s (%s)", methodSignature.getName(), arrayToString(joinPoint.getArgs())) // -> (1)
                : methodSignature.getName() + "()";
        log.info(name);

    }

    private static String arrayToString(final Object... array) {
        return Stream.of(array)
                .map(object -> {
                    if (object.getClass().isArray()) {
                        return arrayToString((Object[]) object);
                    }
                    return Objects.toString(object);
                })
                .collect(Collectors.joining(", "));
    }
}
