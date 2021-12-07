package org.algosketch.server1.repository;

import org.algosketch.server1.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    static Map<Long, Member> store = new HashMap<>();
    static long sequence = 0L;

    @Override
    public Member createMember(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member updateMember(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return store.values().stream().findAny();
    }
}
