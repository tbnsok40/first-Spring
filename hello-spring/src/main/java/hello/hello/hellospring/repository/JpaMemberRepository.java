package hello.hello.hellospring.repository;

import hello.hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; //JPA는 entityManager라는걸 생성해준다.
    // 만들어준걸 인젝션 받으면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist: 영속하다(저장)
        return member; //(이렇게 간단한데) jpa가 인서트 쿼리 만들어 알아서 다 저장한다
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 기존 쿼리문은, select id, name등 속성이름을 사용해야했다면, JPA에서는
        // Member(class=entity)를 엔티티로 매핑했기에 곧바로 사용할 수 있게 된다(m은 Member 엔티티의 alias)
        // 멤버 클래스를 멤버 엔티티라고 불러도 되는 건가?
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
