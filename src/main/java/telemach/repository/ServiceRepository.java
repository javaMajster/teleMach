package telemach.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telemach.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
