package mins.mall.domain.item;

import lombok.Data;
import mins.mall.domain.item.common.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ALBUM") // DTYPE 구분자의 기본값은 엔티티명.
@Data
public class Album extends Item {
    private String artist;
}
