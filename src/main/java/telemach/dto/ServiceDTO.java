package telemach.dto;


public class ServiceDTO {

    private Long serviceId;
    private String service;
    private Boolean value;
    private String comment;

    public ServiceDTO() {
    }

    public ServiceDTO(Long serviceId, String service, Boolean value, String comment) {
        this.serviceId = serviceId;
        this.service = service;
        this.value = value;
        this.comment = comment;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
