package telemach.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telemach.model.Address;

import javax.persistence.Entity;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {



    Page<Address> findAll(Specification<Entity> spec, Pageable page);


    @Query("select a from Address a where a.addressId = ?1 or a.city = ?2 or" +
            " a.postNo = ?3 or a.post = ?4 or a.street = ?5 or a.streetNo = ?6 ")
    Address getAddressByProperty(Long id, String city, Integer postNo,String post,String street,String streetNo);
}
