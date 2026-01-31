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

    // Custom query to find patients by their phone number
    Optional<Patient> findByPhone(String phone);

    // New custom query to find patients by either email or phone number
    Optional<Patient> findByEmailOrPhone(String email, String phone);

    // You can add more custom queries here based on your application’s needs
}
