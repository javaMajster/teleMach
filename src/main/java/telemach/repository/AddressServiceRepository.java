package telemach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telemach.model.AddressService;


@Repository
public interface AddressServiceRepository extends JpaRepository<AddressService, Long> {



}
