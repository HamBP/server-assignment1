package org.algosketch.server1.repository;

import org.algosketch.server1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member createMember(Member member);
    public Member updateMember(Member member);
    public List<Member> findAll();
    public Optional<Member> findMemberById(Long id);
    public Optional<Member> findMemberByEmail(String email);
}
