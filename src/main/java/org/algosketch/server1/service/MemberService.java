package org.algosketch.server1.service;

import org.algosketch.server1.domain.Member;
import org.algosketch.server1.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> join(Member member) {
        // 이메일 중복
        if(memberRepository.findMemberByEmail(member.getEmail()).isPresent()) {
            return Optional.empty();
        }

        return Optional.ofNullable(memberRepository.createMember(member));
    }

    public Optional<Member> updateMember(Long memberId, Member member) {
        if(memberRepository.findMemberById(memberId).isEmpty()) {
            return Optional.empty();
        }

        member.setId(memberId);
        return Optional.ofNullable(memberRepository.updateMember(member));
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }
}
