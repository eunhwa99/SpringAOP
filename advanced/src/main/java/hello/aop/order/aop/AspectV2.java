package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
        /*
        1) joinPoint.proceed()의 리턴은 주로 타깃 메서드의 결과입니다. 타깃 메서드의 결과가 있다면 result를 가지고 여러가지를 할 수 있습니다. 예를 들어 결과를 로깅할 수도 있고, 결과로 받은 값을 변환해서 다시 클라이언트에 전달해줄 수도 있습니다. 또한 결과로 받은 값에 대해 검증을 해줄 수도 있습니다.

2) return 하게 되면 해당 타깃 메서드를 호출한 클라이언트에게 전달됩니다. 예를 들어 타깃 메서드가 반환값이 있는 메서드라면 이를 호출한 클라이언트에서는 반환값을 받아서 처리해야 하기 때문입니다.
         */
    }

}
