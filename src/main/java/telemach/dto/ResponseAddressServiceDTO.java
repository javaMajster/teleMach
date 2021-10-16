package telemach.dto;

import java.util.Set;

public class ResponseAddressServiceDTO {

    private AddressDTO addressDTO;
    private Set<ServiceDTO> serviceDTOS;

    public ResponseAddressServiceDTO() {
    }

    public ResponseAddressServiceDTO(AddressDTO addressDTO, Set<ServiceDTO> serviceDTOS) {
        this.addressDTO = addressDTO;
        this.serviceDTOS = serviceDTOS;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public Set<ServiceDTO> getServiceDTOS() {
        return serviceDTOS;
    }

    public void setServiceDTOS(Set<ServiceDTO> serviceDTOS) {
        this.serviceDTOS = serviceDTOS;
    }
}
