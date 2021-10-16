package telemach.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Address implements Serializable {
    private static final long serialVersionUID = -3941957588586266795L;

    private Long addressId;
    @NotBlank
    private String streetNo;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String post;
    @NotNull
    private Integer postNo;

    public Address() {
    }

    public Address(Long addressId, String streetNo, String street, String city, String post, Integer postNo) {
        this.addressId = addressId;
        this.streetNo = streetNo;
        this.street = street;
        this.city = city;
        this.post = post;
        this.postNo = postNo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAddressId() {
        return addressId;
    }


    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getPostNo() {
        return postNo;
    }

    public void setPostNo(Integer postNo) {
        this.postNo = postNo;
    }
}
