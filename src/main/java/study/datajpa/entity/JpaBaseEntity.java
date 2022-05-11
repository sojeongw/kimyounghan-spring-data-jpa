package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

// 상속처럼 프로퍼티를 테이블에서 내려서 쓸 수 있는 애너테이션
// 진짜 상속과는 다르다.
@MappedSuperclass
@Getter
public class JpaBaseEntity {

    // 실수로라도 DB 값이 변경되지 않게 막는다.
    @Column(updatable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    // 영속화 하기 전에 발생시키는 이벤트
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        // 등록과 수정을 처음부터 똑같이 맞춰둔다.
        // 값에 null이 있으면 쿼리가 지저분해질 수 있고 created와 값이 같으면 최초 값이라는 것을 알 수 있어 편하다.
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
