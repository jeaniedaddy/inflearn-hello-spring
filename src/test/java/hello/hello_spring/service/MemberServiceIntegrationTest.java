package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

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