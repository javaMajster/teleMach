package telemach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address_service")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class AddressService implements Serializable {
    private static final long serialVersionUID = -3941957588586266795L;
    @JsonIgnore
    private Long addressServiceId;
    private Long addressIdFk;
    private Long serviceIdFk;

    public AddressService() {
    }

    public AddressService(Long addressServiceId, Long addressIdFk, Long serviceIdFk) {
        this.addressServiceId = addressServiceId;
        this.addressIdFk = addressIdFk;
        this.serviceIdFk = serviceIdFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAddressServiceId() {
        return addressServiceId;
    }

    public void setAddressServiceId(Long addressServicesId) {
        this.addressServiceId = addressServicesId;
    }


    @JoinColumn(name = "address_id_fk",referencedColumnName ="address_id",nullable = false  )
    public Long getAddressIdFk() {
        return addressIdFk;
    }

    public void setAddressIdFk(Long addressIdFk) {
        this.addressIdFk = addressIdFk;
    }


    @JoinColumn(name = "service_id_fk",referencedColumnName ="service_id",nullable = false )
    public Long getServiceIdFk() {
        return serviceIdFk;
    }

    public void setServiceIdFk(Long serviceIdFk) {
        this.serviceIdFk = serviceIdFk;
    }






}
