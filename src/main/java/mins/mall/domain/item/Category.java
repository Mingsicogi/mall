package mins.mall.domain.item;

import lombok.Data;
import mins.mall.domain.common.BasicEntity;
import mins.mall.domain.item.common.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category extends BasicEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // self relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "cotegory_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();
}
