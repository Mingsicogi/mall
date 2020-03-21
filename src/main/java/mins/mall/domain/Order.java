package mins.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 편의 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
}
