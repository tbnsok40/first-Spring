package hello.hello.hellospring;

import hello.hello.hellospring.Service.MemberService;
import hello.hello.hellospring.repository.MemberRepository;
import hello.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean //스프링 빈을 등록할 것이란 의미
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 생성자에 memberRepository를 넣어줘야 한다.
    }
    @Bean
    public MemberRepository memberRepository(){ // MemberRepository는 인터페이스이기에 new 사용불가
        return new MemoryMemberRepository(); //MemoryMemberRepository는 구현체
    }
}
