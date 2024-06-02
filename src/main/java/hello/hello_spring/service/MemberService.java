package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
  private MemberRepository memberRepository;

//  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member){
    checkDuplicatedName(member);
    memberRepository.save(member);
    return member.getId();
  }

  public List<Member> findMembers(){
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }

  private void checkDuplicatedName(Member member) {
    memberRepository.findByName(member.getName())
      .ifPresent(m-> {
        throw new IllegalStateException("duplicated name");
      });
  }
}
