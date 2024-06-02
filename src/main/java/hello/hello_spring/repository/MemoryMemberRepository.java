package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
  private static Map<Long, Member> store = new HashMap<>();
  private static Long id = 0L;
  @Override
  public Member save(Member member) {
    member.setId(++id);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream().filter(mem -> mem.getName().equals(name)).findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public void clearStore() {
    this.store.clear();
  }
}
