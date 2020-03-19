package mins.mall.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipCode;

//    private Long teamId;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, String city, String street, String zipCode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
