package hello.hello.hellospring;

import hello.hello.hellospring.Service.MemberService;
import hello.hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean // spring container에 bean을 등록할 것을 알린다.
    public MemberRepository memberRepository(){

//        return new JdbcMemberRepository(dataSource); // dataSource 안넣어주면 에러
        return new JdbcTemplateMemberRepository(dataSource);
       // JdbcTemplateMemberRepository에서 만든 내용을 여기서 조립(끼워넣는다)
    }
    // MemberRepository는 인터페이스이기에 new 사용불가

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 생성자에 memberRepository를 넣어준다(DI)
    }


//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
