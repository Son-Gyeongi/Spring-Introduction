package hello.hellospring;

import hello.hellospring.Repository.*;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration // @Configuration 스프링 빈으로 관리됨
public class SpringConfig {

    /*
    // jdbc에 dataSource 파라미터 필요
    // 스프링이 제공해준다.
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */

    /*
//    @PersistenceContext 이렇게도 할 수 있다.
    private EntityManager em;

    @Autowired // Spring에서 DI해준다.
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */

    // 스프링 데이터 JPA가 만들어놓은 구현체가 등록이 된다.
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // spring Bean으로 등록할 거다
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); 순수 JDBC
//        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplate
//        return new JpaMemberRepository(em); // JPA
//    }

    // AOP 스프링 빈에 등록 / @Component를 써도 된다.
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
