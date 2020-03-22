package mins.mall.domain.item;

import lombok.Data;
import mins.mall.domain.item.common.Item;

import javax.persistence.Entity;

@Entity
@Data
public class Book extends Item {

    private String author;
    private String isbn;
}
