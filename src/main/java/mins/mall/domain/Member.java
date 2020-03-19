package mins.mall.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Member(String name, String city, String street, String zipCode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
