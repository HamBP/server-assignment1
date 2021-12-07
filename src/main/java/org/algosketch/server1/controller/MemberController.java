package org.algosketch.server1.controller;

import org.algosketch.server1.domain.Member;
import org.algosketch.server1.domain.MemberForm;
import org.algosketch.server1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity join(@RequestBody Member member) {
        // 이메일 중복, 이메일이 없을 경우 예외 처리 필요.
        System.out.println("MemberController.join : " + member.getEmail());
        Member newMember = new Member();
        newMember.setEmail(member.getEmail());
        return ResponseEntity.of(memberService.join(newMember));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Member> patchMember(@RequestBody Member member, @PathVariable Long memberId) {
        System.out.println("MemberController.patchMember : " + memberId);
        return ResponseEntity.of(memberService.updateMember(memberId, member));
    }

    @GetMapping("")
    @ResponseBody
    public List<Member> getMembers() {
        System.out.println("MemberController.getMembers");
        return memberService.getMembers();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId")Long memberId) {
        System.out.println("MemberController.getMember : " + memberId);
        memberService.getMember(memberId).ifPresent(m -> {
            System.out.println("m = " + m.getEmail());
        });
        return ResponseEntity.of(memberService.getMember(memberId));
    }
}
