package mins.mall.domain.common;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 모든 엔티티의 기본이 되는 엔티티. 따로 테이블은 생성되지 않고, 엔티티 속성들만 상속되어 사용됨.
@MappedSuperclass
public abstract class BasicEntity { // 항상 상속 받아 사용되므로, abstract class로 사용하는 것을 추천함.
    private String regMember;
    private LocalDateTime regLocalDateTime;
    private String modMember;
    private LocalDateTime modLocalDateTime;
}
