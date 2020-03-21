package mins.mall.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member") // 연관 관계의 주인
    private List<Order> orders = new ArrayList<>();

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
