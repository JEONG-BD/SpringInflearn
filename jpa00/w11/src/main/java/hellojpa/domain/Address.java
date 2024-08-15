package hellojpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String zipCode;
    private String address;

    public Address() {

    }

    public Address(String city, String zipCode, String address) {
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }
}
