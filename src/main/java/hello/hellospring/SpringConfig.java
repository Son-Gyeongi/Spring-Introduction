package hello.hellospring;

import hello.hellospring.Repository.JdbcMemberRepository;
import hello.hellospring.Repository.JdbcTemplateMemberRepository;
import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
자바 코드로 직접 스프링 빈 등록하기
 */
@Configuration // @Configuration 스프링 빈으로 관리됨
public class SpringConfig {

    // jdbc에 dataSource 파라미터 필요
    // 스프링이 제공해준다.
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // spring Bean으로 등록할 거다
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); 순수 JDBC
        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplate
    }
}
