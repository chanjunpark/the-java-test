package com.example.thejavatest.member;

import com.example.thejavatest.domain.Member;

import java.util.Optional;

public interface MemberService {
    public Optional<Member> findById(Long memberId);

    void validate(Long memberId);
}
