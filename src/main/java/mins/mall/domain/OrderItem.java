package mins.mall.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    private Long orderId;
    private Long itemId;
    private int price;
    private int stockQuantity;
}
