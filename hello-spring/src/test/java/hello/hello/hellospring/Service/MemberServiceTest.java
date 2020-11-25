package hello.hello.hellospring.Service;

import hello.hello.hellospring.domain.Member;
import hello.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // test코드는 한글로 이름 지어도 ㄱㅊ
    @Test
    @Commit // test지만 실제로 DB에 올린다
    void 회원가입() {

        // recommended tdd method (꼭 이 상황이 맞는 것은 아니다)
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
// test 는 정상 플로우도 중요하지만, 예외 플로우도 중요하다.

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        // when
//        memberService.join(member1);
//        memberService.join(member2); //validate에서 걸려야한다.
        memberService.join(member1);

        // try catch 대신 더 좋은 문법으로 대체
//        try{
//            memberService.join(member2);
//            fail("예외가 발생해야 한다.");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");


        // then
    }
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}