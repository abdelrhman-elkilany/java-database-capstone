import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the Appointment ID
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    private LocalDateTime appointmentTime;  // Using LocalDateTime for better precision

    private String status; // e.g., "Scheduled", "Completed", "Cancelled"
    
    private LocalDateTime createdAt;

    // Constructor
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, String status, LocalDateTime createdAt) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Method to get a summary of the appointment details
    public String getAppointmentSummary() {
        return "Appointment ID: " + this.appointmentId + "\n" +
                "Doctor: " + this.doctor.getFullName() + "\n" +
                "Patient: " + this.patient.getFullName() + "\n" +
                "Appointment Time: " + this.appointmentTime + "\n" +
                "Status: " + this.status + "\n" +
                "Created At: " + this.createdAt;
    }

    // Method to display appointment details
    public void displayAppointmentDetails() {
        System.out.println("Appointment ID: " + this.appointmentId);
        System.out.println("Doctor: " + this.doctor.getFullName());
        System.out.println("Patient: " + this.patient.getFullName());
        System.out.println("Appointment Time: " + this.appointmentTime);
        System.out.println("Status: " + this.status);
        System.out.println("Created At: " + this.createdAt);
    }

    // Optional: Override the toString() method to represent the appointment object as a string
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", appointmentTime=" + appointmentTime +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
