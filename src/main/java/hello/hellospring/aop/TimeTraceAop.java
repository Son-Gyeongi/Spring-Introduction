package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // 스프링 빈에 등록
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 공통 관심 사항 타켓팅
    // 매뉴얼 보고 따라하면 된다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 시간로직
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            // 다음 메서드로 진행
            return joinPoint.proceed(); // 오류날 떄 리턴
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }
}
