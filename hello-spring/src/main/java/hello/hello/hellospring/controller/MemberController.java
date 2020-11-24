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
    private final MemberService memberService; // spring 컨테이너에 등록

    @Autowired // 자동 주입 (memberService)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // data 조회할 땐 getMapping
    @GetMapping("/members/new") // url로 보낸다 => createMemberForm.html render
    public String createForm2(){
        return "members/createMemberForm";
    }

    // data 넘길 때 postMapping
    // createMemberForm.html의 <form action = "/members/new" method="post">
    @PostMapping("/members/new")
    public String create2(MemberForm form){ // MemberForm 클래스의 name 변수에 값이 넘어온다
        Member member = new Member();

        member.setName(form.getName());
        member.setGender(form.getGender());

        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // findMembers()메소드 자체가 리턴형이 List<Member>
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
