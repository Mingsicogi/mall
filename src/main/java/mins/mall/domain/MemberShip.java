package mins.mall.domain;

import javax.persistence.*;

@Entity
public class MemberShip {

    @Id @GeneratedValue
    @Column(name = "memberShip_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MemberShipGrade memberShipGrade;
}
