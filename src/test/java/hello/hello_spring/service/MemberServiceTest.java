package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
  MemberService memberService;
  MemberRepository memberRepository;


  @BeforeEach
  void beforeEach(){
    this.memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(this.memberRepository);

  }
  @AfterEach
  void afterEach(){
    memberRepository.clearStore();
  }
  @Test
  void join() {
    Member a = new Member();
    a.setName("spring1");
    Long memberId = memberService.join(a);
    String name = memberService.findOne(memberId).get().getName();
    assertThat(name).isEqualTo("spring1");
  }

  @Test
  void joinDuplicated() {
    Member a = new Member();
    a.setName("spring1");
    memberService.join(a);

    Member b = new Member();
    b.setName("spring1");
//    try {
//      memberService.join(b);
//      fail();
//    } catch(IllegalStateException e){
//    }
    IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> {
      memberService.join(b);
    });
    assertThat(illegalStateException.getMessage()).isEqualTo("duplicated name");
  }
  @Test
  void findMembers() {
    Member a = new Member();
    a.setName("spring1");
    memberService.join(a);

    Member b = new Member();
    b.setName("spring2");
    memberService.join(b);

    List<Member> members = memberService.findMembers();
    assertThat(members.size()).isEqualTo(2);
  }

  @Test
  void findOne() {
  }
}