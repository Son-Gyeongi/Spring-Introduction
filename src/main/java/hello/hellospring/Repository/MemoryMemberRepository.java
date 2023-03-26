package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 메모리라서 저장을 어딘가 해야한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 멤버 저장시 sequence 값 올려준다.
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 요즘에는 Null이 나오는 경우 Optional로 감싼다
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store의 values가 멤버들이다.
        return new ArrayList<>(store.values());
    }

    // store를 싹 비운다.
    public void clearStore() {
        store.clear();
    }
}
