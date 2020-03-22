package mins.mall.domain.item.common;

import lombok.Data;
import mins.mall.domain.common.BasicEntity;
import mins.mall.domain.item.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// InheritanceType.TABLE_PER_CLASS, default strategy. 한테이블에 모든 컬럼을 생성함.
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // 정규화된 테이블의 데이터 생성시 상위 테이블에서 하위 테이블 타입구분.
@Data
public abstract class Item extends BasicEntity { // 상속 구조의 상위 엔티티로 단독으로 사용할 수 없게, abstract class로 설계해야 좋음.

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // 다대다 관계 주인 설정
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
