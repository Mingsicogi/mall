package mins.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Data
public class Order {

    @Id @GeneratedValue
    private Long id;

    private Long memberId;
    private LocalDateTime orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
