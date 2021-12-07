package org.algosketch.server1.service;

import org.algosketch.server1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// @Transactional database 로 테스트할 때
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Test
    void join() {
        Member member = new Member();
        member.setEmail("test@gmail.com");

        Member result = memberService.join(member).get();

        Assertions.assertThat(result.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("중복 이메일")
    void joinDuplicate() {
        Member member1 = new Member();
        member1.setEmail("test@gmail.com");

        Member member2 = new Member();
        member2.setEmail("test@gmail.com");

        memberService.join(member1);
        Optional<Member> result = memberService.join(member2);

        Assertions.assertThat(result.isEmpty()).isEqualTo(true);
    }

    @Test
    void updateMember() {
        Member member = new Member();
        member.setEmail("test@gmail.com");
        memberService.join(member);
        member.setAge(24);
        member.setName("test");

        Member result = memberService.updateMember(1L, member).get();

        Assertions.assertThat(result.getName()).isEqualTo(member.getName());
        Assertions.assertThat(result.getAge()).isEqualTo(member.getAge());
    }

    @Test
    void getMember() {
        Member member = new Member();
        member.setEmail("test@gmail.com");
        memberService.join(member);

        Member result = memberService.getMember(1L).get();

        Assertions.assertThat(result.getEmail()).isEqualTo(member.getEmail());
    }
}