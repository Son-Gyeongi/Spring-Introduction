package hello.hellospring.service;

import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    /*
    memberService를 생성할 때마다 MemoryMemberRepository()를 직접 넣어준다.
     */
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 실행할 때마다 각각 생성해준다.
    // 테스트는 독립적으로 실행되어야 하기 때문에
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // -> 같은 MemoryMemberRepository 사용할 수 있다
    }

    // 메서드 실행 후 저장소(DB) 싹 비워준다.
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 무언가가 주어진다
        Member member = new Member();
        member.setName("hello");

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

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}