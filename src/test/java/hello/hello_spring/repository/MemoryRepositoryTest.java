package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryRepositoryTest {
  private  MemberRepository repo = new MemoryMemberRepository();
  @AfterEach
  public void afterEach(){
    this.repo.clearStore();;
  }
  @Test
  public void save(){
    Member a = new Member();
    a.setName("steve");
    repo.save(a);

    Member result = repo.findById(a.getId()).get();
    assertThat(result).isEqualTo(a);
  }

  @Test
  public void findByName(){
    Member a = new Member();
    a.setName("spring1");
    repo.save(a);

    Member b = new Member();
    b.setName("spring2");
    repo.save(b);

    Member result = repo.findByName("spring1").get();
    assertThat(result).isEqualTo(a);
  }

  @Test
  public void findAll(){
    Member a = new Member();
    a.setName("spring1");
    repo.save(a);

    Member b = new Member();
    b.setName("spring2");
    repo.save(b);

    assertThat(repo.findAll().size()).isEqualTo(2);
  }
}
