package hello.hello.hellospring;

import hello.hello.hellospring.Service.MemberService;
import hello.hello.hellospring.repository.JpaMemberRepository;
import hello.hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    // 기존
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    // JPA사용할 때
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }



    @Bean // spring container에 bean을 등록할 것을 알린다.
    public MemberRepository memberRepository(){

//        return new JdbcMemberRepository(dataSource); // dataSource 안넣어주면 에러
//        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplateMemberRepository에서 만든 내용을 여기서 조립(끼워넣는다)
        return new JpaMemberRepository(em);

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
