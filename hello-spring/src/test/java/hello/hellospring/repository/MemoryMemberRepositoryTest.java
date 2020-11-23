package hello.hellospring.repository;

import hello.hello.hellospring.domain.Member;
import hello.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Test case는 상위 클래스에서도 돌릴 수 있고, 하위개별 클래스에서도 돌릴 수 있다.
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 중요: TDD, test 주도 개발, 테스트 모듈을 먼저 만들고 후에 본 모델을 만드는것
    // 테스트 코드 없이 개발 한다는 것은(협업시) 불가능 => 정말 문제가 많이 발생하게 된다.
    // test가 끝날때마다 메모리 클리어해준다. because test case 는 순서대로 동작하지 않기 때문(무작위 동작)
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, "lim");
        assertThat(member).isEqualTo(result); // 윗줄과 다른 방법으로 assertion확인
//        assertThat(member).isEqualTo(null); // 윗줄과 다른 방법으로 assertion확인
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
    }
}
