package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
  MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/tree")
  @ResponseBody
  public String member(){
    Member a = new Member();
    a.setName("steve");
    this.memberService.join(a);
    return "tree";
  }

  @GetMapping("/members/new")
  public String createMemberForm(){
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String saveMemberForm(MemberForm memberForm){
    Member a = new Member();
    a.setName(memberForm.getName());
    this.memberService.join(a);

    System.out.println("name: " + a.getName());
    return "redirect:/";
  }

  @GetMapping("/members")
  public String members(Model model){
    List<Member> members = this.memberService.findMembers();
    model.addAttribute("members",members);
    return "members/memberList";
  }

}
