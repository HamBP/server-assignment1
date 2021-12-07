package org.algosketch.server1.controller;

import org.algosketch.server1.domain.Member;
import org.algosketch.server1.domain.MemberForm;
import org.algosketch.server1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 유효성 검사도 해야하는데 귀찮습니다. 봐주시죠!!
    @PostMapping("")
    public ResponseEntity join(@RequestBody Member member) {
        // 이메일이 비어있을 경우
        if(member.getEmail().isEmpty() || member.getEmail().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email 을 보내주셔야죠!!");
        }

        System.out.println("MemberController.join : " + member.getEmail());
        Member newMember = new Member();
        newMember.setEmail(member.getEmail());

        Optional<Member> result = memberService.join(newMember);
        if(result.isPresent()) {
            return ResponseEntity.of(memberService.join(newMember));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("혹시.. email 이 중복된 거 아닐까요?");
        }
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
