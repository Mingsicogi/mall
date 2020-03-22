package mins.mall.domain.member;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MemberShip {

    @Id @GeneratedValue
    @Column(name = "memberShip_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MemberShipGrade memberShipGrade;
}
