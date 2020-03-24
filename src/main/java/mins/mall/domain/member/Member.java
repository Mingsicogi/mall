package mins.mall.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mins.mall.domain.common.BasicEntity;
import mins.mall.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true) // 연관 관계의 주인
    private List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberShip_id")
    private MemberShip memberShip;

    @Embedded
    // 컬럼명 재정의
    @AttributeOverrides(value = {
            @AttributeOverride(name = "city", column = @Column(name = "oldAddress_city")),
            @AttributeOverride(name = "street", column = @Column(name = "oldAddress_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "oldAddress_zipCode"))

    })
    private Address oldAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = {
            @JoinColumn(name = "member_id")
    })
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS_HIST", joinColumns = {
            @JoinColumn(name = "member_id")
    })
    private List<Address> addressesHist = new ArrayList<>();

    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
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
