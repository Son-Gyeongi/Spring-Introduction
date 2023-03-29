package hello.hellospring.service;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트가 끝난 후 테스트에서 실행된 쿼리들을 ROLLBACK 한다. 다음 테스트를 또 반복해서 할 수 있다.
class MemberServiceIntegrationTest {
    // 테스트는 제일 끝단에 있기 때문에 테스트 코드 할 때는 제일 편한 방법을 쓰면된다.
    @Autowired MemberService memberService;
    /*
    @Autowired MemoryMemberRepository memberRepository;
     MemoryMemberRepository 대신에 MemberRepository를 써준다.
     그러면 configuration에 있는 스프링에 등록된 구현체가 올라올거다
     */
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given 무언가가 주어진다
        Member member = new Member();
        member.setName("spring");

        //when 실행했을 때
        // memberService에서 join검증
        Long saveId = memberService.join(member);

        //then 결과가 이렇게 나와야한다. 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        /*      이러한 방법도 있다. 그리고 더 좋은 문법 assertThrows
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
 */
        // () -> memberService.join(member2) 이 로직이 실행될 때
        // IllegalStateException.class 이 예외가 터져야한다.
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 메세지 검증 assertThrows는 반환이 된다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // e를 검증하면 된다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }
}