package telemach.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telemach.exception.ResourceNotFoundException;
import telemach.model.Service;
import telemach.repository.ServiceRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/service" ,
                produces={"application/json", "application/xml"})

public class ServiceController {

    Logger logger = LoggerFactory.getLogger(ServiceController.class);



    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/service")
    public List<Service> getAllService() {
        logger.info("getting all products");
        return serviceRepository.findAll();
    }


    @PostMapping(value = "/service" ,
            consumes = {"application/json", "application/xml"})
    public Service createService(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @GetMapping("/service/{id}")
    public Service getServiceById(@PathVariable(value = "id") Long serviceId) {
        logger.info(String.format("Finding service id: %s ",serviceId));
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "serviceId", serviceId));
    }

    @PutMapping(value = "/service/{id}",
            consumes = {"application/json", "application/xml"})
    public Service updateService(@PathVariable(value = "id") Long serviceId,
                                 @Valid @RequestBody Service serviceBoddy) {

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "serviceId", serviceId));


        service.setService(serviceBoddy.getService());
        service.setComment(serviceBoddy.getComment());
        service.setValue(service.getValue());

        Service updateAddress = serviceRepository.save(service);
        logger.info(String.format("Service id: %s was updated", service.getServiceId()));

        return updateAddress;
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<?> deleteService(@PathVariable(value = "id") Long serviceId) {
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "serviceId", serviceId));

        serviceRepository.delete(service);
        logger.info(String.format("Service id:  %s deleted ", service));
        return ResponseEntity.ok().build();
    }

}
