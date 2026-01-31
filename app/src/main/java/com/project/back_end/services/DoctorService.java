import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // 1. Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // 2. Get a doctor by ID
    public Optional<Doctor> getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId);
    }

    // 3. Create a new doctor
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // 4. Update an existing doctor
    public Doctor updateDoctor(int doctorId, Doctor doctorDetails) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctorId);
        if (existingDoctor.isPresent()) {
            Doctor updatedDoctor = existingDoctor.get();
            updatedDoctor.setFirstName(doctorDetails.getFirstName());
            updatedDoctor.setLastName(doctorDetails.getLastName());
            updatedDoctor.setSpecialty(doctorDetails.getSpecialty());
            updatedDoctor.setEmail(doctorDetails.getEmail());
            updatedDoctor.setPhone(doctorDetails.getPhone());
            // You can add more fields to be updated
            return doctorRepository.save(updatedDoctor);
        }
        return null; // Or throw an exception if the doctor is not found
    }

    // 5. Delete a doctor
    public boolean deleteDoctor(int doctorId) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctorId);
        if (existingDoctor.isPresent()) {
            doctorRepository.deleteById(doctorId);
            return true;
        }
        return false; // Doctor not found
    }

    // 6. Find doctors by specialty
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    // 7. Find doctors by first name
    public List<Doctor> getDoctorsByFirstName(String firstName) {
        return doctorRepository.findByFirstName(firstName);
    }

    // 8. Find doctors by last name
    public List<Doctor> getDoctorsByLastName(String lastName) {
        return doctorRepository.findByLastName(lastName);
    }
}
