package mins.mall.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    private int orderPrice;
    private int itemCount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // 편의 기능
    public void setOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }
}
