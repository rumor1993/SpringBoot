package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }


    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring1");
        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("spring1").get();
        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {

        Member member = new Member();
        member.setName("spring1");
        memoryMemberRepository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
