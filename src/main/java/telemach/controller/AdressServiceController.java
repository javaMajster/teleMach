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
        List<AddressService> addressServices = addressServiceRepository.findAll();
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

            Set<ServiceDTO> serviceDTOS = new HashSet<>();
            for (AddressService addressService : addressServices){
                if (addressService.getAddressIdFk().equals(address.getAddressId())){
                Service servicesE = serviceRepository.findById(addressService.getServiceIdFk())
                        .orElseThrow(() -> new ResourceNotFoundException("Service", "serviceId", addressService.getServiceIdFk()));
                    ServiceDTO serviceDTO = new ServiceDTO(
                            servicesE.getServiceId(),
                            servicesE.getService(),
                            servicesE.getValue(),
                            servicesE.getComment());
                    serviceDTOS.add(serviceDTO);
                }

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
    public AddressService createAddressService(@Valid @RequestBody AddressService addressService) {
        logger.info(String.format("Saving addressService id: %s ",addressService.getAddressServiceId()));
        return addressServiceRepository.save(addressService);
    }



    @GetMapping("/addressService/{id}")
    public AddressService getAddressServiceById(@PathVariable(value = "id") Long addressServiceId) {
        logger.info(String.format("Finding address id: %s ",addressServiceId));
        return addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "addressServiceId", addressServiceId));
    }

    @PutMapping(value = "/addressService/{id}",
            consumes = {"application/json", "application/xml"})
    public AddressService updateAddressService(@PathVariable(value = "id") Long addressServiceId,
                                 @Valid @RequestBody AddressService addressServiceBoddy) {

        AddressService addressService = addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "addressServiceId", addressServiceId));


        addressService.setServiceIdFk(addressServiceBoddy.getServiceIdFk());
        addressService.setAddressIdFk(addressServiceBoddy.getAddressIdFk());

        AddressService updateAddress = addressServiceRepository.save(addressService);
        logger.info(String.format("addressService id: %s was updated", addressService.getAddressServiceId()));

        return updateAddress;
    }

    @DeleteMapping("/addressService/{id}")
    public ResponseEntity<?> deleteAddressService(@PathVariable(value = "id") Long addressServiceId) {
        AddressService addressService = addressServiceRepository.findById(addressServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("AddressService", "addressServiceId", addressServiceId));

        addressServiceRepository.delete(addressService);
        logger.info(String.format("AddressService id:  %s deleted ", addressService));
        return ResponseEntity.ok().build();
    }

}
