package mins.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {

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
