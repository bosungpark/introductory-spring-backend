package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired 
    public MemberController(MemberService memberService) {
        this.memberService= memberService;
    }

    @GetMapping(value="/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member= new Member();
        member.setName(form.getName());

        System.out.println(member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members= memberService.findMembers();
        
        System.out.println(members);

        model.addAttribute("Members", members);
        return "members/memberList";
    }
}
