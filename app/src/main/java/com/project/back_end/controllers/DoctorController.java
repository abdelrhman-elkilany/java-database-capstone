import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;  // Assuming TokenService handles token validation

    // 1. Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // 2. Get a doctor by ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") int doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. Create a new doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor newDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
    }

    // 4. Update an existing doctor
    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") int doctorId, @RequestBody Doctor doctorDetails) {
        Optional<Doctor> existingDoctor = doctorService.getDoctorById(doctorId);
        if (existingDoctor.isPresent()) {
            Doctor updatedDoctor = doctorService.updateDoctor(doctorId, doctorDetails);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Delete a doctor
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("doctorId") int doctorId) {
        boolean isDeleted = doctorService.deleteDoctor(doctorId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 6. Get doctor's availability
    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<String> getDoctorAvailability(
            @PathVariable("user") String user, 
            @PathVariable("doctorId") int doctorId, 
            @PathVariable("date") String dateStr, 
            @PathVariable("token") String token) {
        
        // Token validation
        if (!tokenService.isValidToken(token)) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.FORBIDDEN);
        }

        // Parse the date string into a LocalDate object
        LocalDate appointmentDate = LocalDate.parse(dateStr);
        
        // Check user role (For demonstration purposes, assume it's "admin", "doctor", or "patient")
        if (!user.equals("admin") && !user.equals("doctor") && !user.equals("patient")) {
            return new ResponseEntity<>("Invalid user role", HttpStatus.FORBIDDEN);
        }

        // Fetch the doctor's availability
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (!doctor.isPresent()) {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }

        boolean isAvailable = doctorService.checkAvailability(doctorId, appointmentDate);
        
        if (isAvailable) {
            return new ResponseEntity<>("Doctor is available", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor is not available", HttpStatus.OK);
        }
    }
}
