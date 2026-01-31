import java.util.Date;

public class Appointment {

    // Attributes
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private Date appointmentDate;
    private String status; // e.g., "Scheduled", "Completed", "Cancelled"
    private Date createdAt;

    // Constructor
    public Appointment(int appointmentId, int doctorId, int patientId, Date appointmentDate, String status, Date createdAt) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Method to get a summary of the appointment details
    public String getAppointmentSummary() {
        return "Appointment ID: " + this.appointmentId + "\n" +
                "Doctor ID: " + this.doctorId + "\n" +
                "Patient ID: " + this.patientId + "\n" +
                "Appointment Date: " + this.appointmentDate + "\n" +
                "Status: " + this.status + "\n" +
                "Created At: " + this.createdAt;
    }

    // Method to display appointment details
    public void displayAppointmentDetails() {
        System.out.println("Appointment ID: " + this.appointmentId);
        System.out.println("Doctor ID: " + this.doctorId);
        System.out.println("Patient ID: " + this.patientId);
        System.out.println("Appointment Date: " + this.appointmentDate);
        System.out.println("Status: " + this.status);
        System.out.println("Created At: " + this.createdAt);
    }

    // Optional: Override the toString() method to represent the appointment object as a string
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", appointmentDate=" + appointmentDate +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
