package mins.mall.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;
    private String street;
    private String zipCode;
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    // 양방향 1:1 관계설정(주인설정)
    @OneToOne(mappedBy = "delivery")
    private Order order;
}
