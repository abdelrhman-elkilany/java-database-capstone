import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    // Inject the AppointmentRepository to interact with the database
    @Autowired
    private AppointmentRepository appointmentRepository;

    // 1. Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 2. Get an appointment by ID
    public Optional<Appointment> getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    // 3. Create a new appointment
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // 4. Update an existing appointment
    public Appointment updateAppointment(int appointmentId, Appointment appointmentDetails) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
        if (existingAppointment.isPresent()) {
            Appointment updatedAppointment = existingAppointment.get();
            updatedAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            updatedAppointment.setStatus(appointmentDetails.getStatus());
            // Update any other fields if necessary
            return appointmentRepository.save(updatedAppointment);
        }
        return null; // Or throw an exception if needed
    }

    // 5. Delete an appointment
    public boolean deleteAppointment(int appointmentId) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
        if (existingAppointment.isPresent()) {
            appointmentRepository.deleteById(appointmentId);
            return true;
        }
        return false; // Appointment not found
    }

    // 6. Get appointments for a specific doctor
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // 7. Get appointments for a specific patient
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // 8. Get appointments for a specific doctor on a specific date
    public List<Appointment> getAppointmentsByDoctorAndDate(int doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }
}
