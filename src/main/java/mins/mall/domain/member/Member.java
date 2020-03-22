package mins.mall.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mins.mall.domain.common.BasicEntity;
import mins.mall.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BasicEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member") // 연관 관계의 주인
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "memberShip_id")
    private MemberShip memberShip;

    public Member(String name, String city, String street, String zipCode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    /**
     * 연관관계 편의 메소드
     *  - 양방향 연관관계의 경우 연관관계의 주인뿐만 아니라 주인을 정한 엔티티도 값을 셋팅하는 것을 추천.223412
     *  - 데이터를 셋팅하는 과정에서 실수할 수 있기때문에 편의 메소드로 관리.
     *
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
