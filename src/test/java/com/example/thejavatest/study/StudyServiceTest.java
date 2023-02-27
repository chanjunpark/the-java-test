package com.example.thejavatest.study;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.domain.Study;
import com.example.thejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() {


        StudyService studyService = new StudyService(memberService, studyRepository);

        /*
        * Mock 객체 Stubbing
        *  - MOCK 객체가 원하는 방식대로 동작하도록 설정
        * */

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jayden.p@email.com");

        //Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member)); // stubbing
        Mockito.when(memberService.findById(ArgumentMatchers.any())).thenReturn(Optional.of(member)); // Argument Matcher 를 이용해 파라미터 값을 범용적으로 설정할 수도 있다.


        Study study = new Study(10, "java");

        studyService.createNewStudy(1L, study);

        Optional<Member> findByIdMember = memberService.findById(1L);
        assertEquals("jayden.p@email.com", findByIdMember.get().getEmail());

        Mockito.doThrow(new IllegalArgumentException()).when(memberService).validate(1L); // Mock 객체가 예외를 던지도록 stubbing

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        memberService.validate(2L);

    }

    @Test
    void MockitoStubbing() {

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jayden@email.com");

        Mockito.when(memberService.findById(any()))
                .thenReturn(Optional.of(member)) // 첫 번째 호출 될 땐 member가 담긴 Optional 리턴
                .thenThrow(new RuntimeException()) // 두번째 호출 땐 exception
                .thenReturn(Optional.empty()); // 마지막 호출엔 비어있는 Optional 리턴

        Optional<Member> byId = memberService.findById(1L);

        assertEquals("jayden@email.com", byId.get().getEmail());
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(1L);
        });
        assertEquals(Optional.empty(), memberService.findById(1L));

    }

}