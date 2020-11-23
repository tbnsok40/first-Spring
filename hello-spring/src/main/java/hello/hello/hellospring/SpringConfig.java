package hello.hello.hellospring;

import hello.hello.hellospring.Service.MemberService;
import hello.hello.hellospring.repository.MemberRepository;
import hello.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // spring container에 bean을 등록할 것을 알린다.
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    // MemberRepository는 인터페이스이기에 new 사용불가

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 생성자에 memberRepository를 넣어준다(DI)
    }
}
