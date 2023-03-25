package hello.hellospring.Repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 저장소에 저장
    Optional<Member> findById(Long id); // 저장소에서 Id로 데이터 찾아온다.
    Optional<Member> findByname(String name);
    List<Member> findAll(); // 지금까지 저장한 회원 목록 다 찾아준다.
}
