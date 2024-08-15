package hellojpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String strees;
    private String zipCode;

    public Address() {
    }

    public Address(String city, String strees, String zipCode) {
        this.city = city;
        this.strees = strees;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStrees() {
        return strees;
    }

    public void setStrees(String strees) {
        this.strees = strees;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
