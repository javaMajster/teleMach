package telemach.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telemach.exception.ResourceNotFoundException;
import telemach.model.Address;
import telemach.repository.AddressRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")

public class AdressController {

    Logger logger = LoggerFactory.getLogger(AdressController.class);



    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/address")
    public List<Address> getAllAddreses() {
        logger.info("getting all addresses");
        return addressRepository.findAll();
    }

    @PostMapping("/address")
    public Address createProduct(@Valid @RequestBody Address address) {
       return addressRepository.save(address);
    }

    @GetMapping("/address/{id}")
    public Address getProductById(@PathVariable(value = "id") Long addressId) {
        logger.info(String.format("Finding address id: %s ",addressId));
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
    }

    @PutMapping("/address/{id}")
    public Address updateProduct(@PathVariable(value = "id") Long addressId,
                                 @Valid @RequestBody Address addressBoddy) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        
        address.setCity(addressBoddy.getCity());
        address.setPost(addressBoddy.getPost());
        address.setStreet(addressBoddy.getStreet());
        address.setStreetNo(addressBoddy.getStreetNo());
        address.setPostNo(addressBoddy.getPostNo());
        
        Address updateAddress = addressRepository.save(address);
        logger.info(String.format("address id: %s was updated", address.getAddressId()));

        return updateAddress;
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        addressRepository.delete(address);
        logger.info(String.format("Address id:  %s deleted ", address));
        return ResponseEntity.ok().build();
    }

}
