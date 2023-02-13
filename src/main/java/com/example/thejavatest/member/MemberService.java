package com.example.thejavatest.member;

import com.example.thejavatest.domain.Member;

public interface MemberService {
    public Member findById(Long memberId);
}
