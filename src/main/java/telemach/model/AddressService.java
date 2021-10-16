package telemach.model;

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

    private Long addressServicesId;
    private Integer addressIdFk;
    private Integer serviceIdFk;

    public AddressService() {
    }

    public AddressService(Long addressServicesId, Integer addressIdFk, Integer serviceIdFk) {
        this.addressServicesId = addressServicesId;
        this.addressIdFk = addressIdFk;
        this.serviceIdFk = serviceIdFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAddressServicesId() {
        return addressServicesId;
    }

    public void setAddressServicesId(Long addressServicesId) {
        this.addressServicesId = addressServicesId;
    }


    @JoinColumn(name = "address_id_fk",referencedColumnName ="address_id",nullable = false  )
    public Integer getAddressIdFk() {
        return addressIdFk;
    }

    public void setAddressIdFk(int addressIdFk) {
        this.addressIdFk = addressIdFk;
    }


    @JoinColumn(name = "service_id_fk",referencedColumnName ="service_id",nullable = false )
    public Integer getServiceIdFk() {
        return serviceIdFk;
    }

    public void setServiceIdFk(Integer serviceIdFk) {
        this.serviceIdFk = serviceIdFk;
    }






}
