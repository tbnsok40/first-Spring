package hello.hello.hellospring.Service;

import hello.hello.hellospring.domain.Member;
import hello.hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// 컨테이너가 인지할 수 있도록, 스프링 컨테이너에 올려준다 MemberService 클래스를.

public class MemberService {
    private final MemberRepository memberRepository;

    // constructor
    public MemberService(MemberRepository memberRepository) { // 인자는 생성자 호출 시, 외부의 값을 생성자 블록 내부로 전달하는 역할
        this.memberRepository = memberRepository; //MemberService를 외부에서 넣어주도록 바꾼다(memberRepository를 삽입), 의존성 갖는다.
    }

    // 회원가입
    public Long join(Member member){

        // 중복되는 이름 있으면 안된다
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원");
//            // optional이기에 가능한 메소드, result를 optional로 감싼 덕분
//        }); // 이 코드를 아래처럼 변경(개별 메소드화)

        validateDuplicateMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    // 전체회원 조회 기능
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 특정 회원 조회 기능
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
