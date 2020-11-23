package hello.hello.hellospring.repository;

import hello.hello.hellospring.domain.Member;

import java.util.*;
// MemberRepository: 메서드만 있는 인터페이스
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //key값 생성

    @Override
    public Member save(Member member) { // Member 자료형을 저장한다
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //람다식 화살표
                .filter(member -> member.getName().equals(name)) //루프를 돌린다.
                .findAny(); //findAny는 하나라도 찾는 것 => 없으면 optional에 null이 반환 (뭐든 찾아도 optional에 반환)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 회원 레포 테스트 케이스를 작성하여, 위 코드가 제대로 작동하는지 확인해보자 => jUnit이라는 프레임워크로 실행할 수 있다(이게 아니면 실행하기 복잡하고 어려워)

    public void clearStore(){
        store.clear();
    }
}
