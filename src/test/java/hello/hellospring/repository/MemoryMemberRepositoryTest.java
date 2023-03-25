package hello.hellospring.repository;

import hello.hellospring.Repository.MemberRepository;
import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 굳이 public으로 안해도 된다. 다른 애들이 가져다 쓸게 아니니깐
class MemoryMemberRepositoryTest {

//    MemberRepository repository = new MemoryMemberRepository();
    // MemoryMemberRepository만 테스트 하는 거니깐 왼쪽 타입을
// 인터페이스가 아닌 클래스 MemoryMemberRepository로 바꿔준다.
MemoryMemberRepository repository = new MemoryMemberRepository();

// 테스트가 실행이 끝난 후 저장소 싹 지운다. 순서가 상관이 없어진다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test // 저장 잘되는지 확인
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 검증
//        System.out.println("result = "+(result == member));
//        Assertions.assertEquals(member, result); // org.junit.jupiter.api
        // org.assertj.core.api 좀 더 편하게 쓸 수 있게 해준다
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 정교한 테스트를 위해서 member2를 만들자
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByname("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
