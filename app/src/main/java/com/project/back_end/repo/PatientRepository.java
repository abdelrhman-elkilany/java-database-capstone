import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // Custom query to find patients by their first name (can be expanded for more complex queries)
    List<Patient> findByFirstName(String firstName);

    // Custom query to find patients by their last name
    List<Patient> findByLastName(String lastName);

    // Custom query to find patients by their email
    Optional<Patient> findByEmail(String email);

    // You can add more custom queries here based on your application’s needs
}
