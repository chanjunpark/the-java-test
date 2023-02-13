package com.example.thejavatest.study;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.domain.Study;
import com.example.thejavatest.member.MemberService;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study) {

        Member member = memberService.findById(memberId);
        if(member == null) {
            throw new IllegalArgumentException("Member doesn't exist for id : " + memberId);
        }
        study.setOwner(member);

        return studyRepository.save(study);
    }

}
