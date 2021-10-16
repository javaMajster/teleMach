package telemach.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telemach.model.Address;

import javax.persistence.Entity;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {



    Page<Address> findAll(Specification<Entity> spec, Pageable page);
}
