package telemach.dto;


public class AddressServiceDTO {


    private Long addressServicesId;
    private Integer addressIdFk;
    private Integer serviceIdFk;

    public AddressServiceDTO() {
    }

    public AddressServiceDTO(Long addressServicesId, Integer addressIdFk, Integer serviceIdFk) {
        this.addressServicesId = addressServicesId;
        this.addressIdFk = addressIdFk;
        this.serviceIdFk = serviceIdFk;
    }

    public Long getAddressServicesId() {
        return addressServicesId;
    }

    public void setAddressServicesId(Long addressServicesId) {
        this.addressServicesId = addressServicesId;
    }

    public Integer getAddressIdFk() {
        return addressIdFk;
    }

    public void setAddressIdFk(Integer addressIdFk) {
        this.addressIdFk = addressIdFk;
    }

    public Integer getServiceIdFk() {
        return serviceIdFk;
    }

    public void setServiceIdFk(Integer serviceIdFk) {
        this.serviceIdFk = serviceIdFk;
    }
}
