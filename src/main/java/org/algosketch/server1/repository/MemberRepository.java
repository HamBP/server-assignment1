package org.algosketch.server1.repository;

import org.algosketch.server1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findById(Long id);
    public Optional<Member> findByEmail(String email);
}
