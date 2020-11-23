package hello.hello.hellospring.controller;

import hello.hello.hellospring.Service.MemberService;
import hello.hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 이게 붙으면 스프링 컨테이너가 관리하게 된다.
public class MemberController {
    private final MemberService memberService; // spring container에게 등록을 하겠다.

    @Autowired //spring container에 있는 memberService를 가져다 연결해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // data를 넘길 대 postMapping 사용 / data 조회할 땐 getMapping
    public String create(MemberForm form){ // MemberForm 클래스의 name에 값이 넘어온다.
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
