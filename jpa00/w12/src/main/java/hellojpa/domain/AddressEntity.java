package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name= "MEMBER_ID")
    private Member member;


    public AddressEntity() {

    }
    public AddressEntity(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    private String city;

    private String street;

    private String zipCode;

}
