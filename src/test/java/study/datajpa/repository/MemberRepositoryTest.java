package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member memberA = new Member("memberA");
        memberRepository.save(memberA);

        Member findMember = memberRepository.findById(memberA.getId()).get();
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getName()).isEqualTo(memberA.getName());
        assertThat(findMember).isEqualTo(memberA);
    }
}