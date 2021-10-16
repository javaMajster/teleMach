package telemach.controller;


import com.turkraft.springfilter.boot.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telemach.dto.AddressDTO;
import telemach.dto.ResponseAddressServiceDTO;
import telemach.dto.ServiceDTO;
import telemach.exception.ResourceNotFoundException;
import telemach.model.Address;
import telemach.model.AddressService;
import telemach.model.Service;
import telemach.repository.AddressRepository;
import telemach.repository.AddressServiceRepository;
import telemach.repository.ServiceRepository;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/addressService" ,
                produces={"application/json", "application/xml"})
public class AdressServiceController {

    Logger logger = LoggerFactory.getLogger(AdressServiceController.class);



    @Autowired
    AddressServiceRepository addressServiceRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping(value = "/search")
    public Page<Address> search(@Filter Specification<Entity> spec, Pageable page) {
        return addressRepository.findAll(spec, page);
    }

    @GetMapping(value = "/getAllByAddress")
    public List<ResponseAddressServiceDTO> getAllByAddress(){
        List<Address> addressList = addressRepository.findAll();
        List<ResponseAddressServiceDTO> responseAddressServiceDTOS = new ArrayList<>();
        for (Address address : addressList){
            AddressDTO addressDTO = new AddressDTO(
                    address.getAddressId(),
                    address.getStreetNo(),
                    address.getStreet(),
                    address.getCity(),
                    address.getPost(),
                    address.getPostNo());

            ResponseAddressServiceDTO responseAddressServiceDTO = new ResponseAddressServiceDTO();
            responseAddressServiceDTO.setAddressDTO(addressDTO);
            List<Service> services = serviceRepository.findAll();
            Set<ServiceDTO> serviceDTOS = new HashSet<>();
            for (Service service : services){
                ServiceDTO serviceDTO = new ServiceDTO(
                        service.getServiceId(),
                        service.getService(),
                        service.getValue(),
                        service.getComment());
                serviceDTOS.add(serviceDTO);
            }
            responseAddressServiceDTO.setServiceDTOS(serviceDTOS);
            responseAddressServiceDTOS.add(responseAddressServiceDTO);
        }
        return responseAddressServiceDTOS;
    }

    @GetMapping("/addressService")
    public List<AddressService> getAllAddreses() {
        logger.info("getting all products");
        return addressServiceRepository.findAll();
    }


    @PostMapping(value = "/addressService" ,
            consumes = {"application/json", "application/xml"})
    public AddressService createProduct(@Valid @RequestBody AddressService addressService) {
        logger.info(String.format("Saving addressService id: %s ",addressService.getAddressServiceId()));
        return addressServiceRepository.save(addressService);
    }

    @GetMapping("/addressService/{id}")
    public AddressService getProductById(@PathVariable(value = "id") Long addressServiceId) {
        logger.info(String.format("Finding address id: %s ",addressServiceId));
        return addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "id", addressServiceId));
    }

    @PutMapping(value = "/addressService/{id}",
            consumes = {"application/json", "application/xml"})
    public AddressService updateProduct(@PathVariable(value = "id") Long addressServiceId,
                                 @Valid @RequestBody AddressService addressServiceBoddy) {

        AddressService addressService = addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "id", addressServiceId));


        addressService.setServiceIdFk(addressServiceBoddy.getServiceIdFk());
        addressService.setAddressIdFk(addressServiceBoddy.getAddressIdFk());

        AddressService updateAddress = addressServiceRepository.save(addressService);
        logger.info(String.format("addressService id: %s was updated", addressService.getAddressServiceId()));

        return updateAddress;
    }

    @DeleteMapping("/addressService/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long addressServiceId) {
        AddressService addressService = addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "id", addressServiceId));

        addressServiceRepository.delete(addressService);
        logger.info(String.format("AddressService id:  %s deleted ", addressService));
        return ResponseEntity.ok().build();
    }

}
