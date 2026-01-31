import javax.persistence.*;
import java.util.Date;

@Entity
public class Doctor {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
    private Long id;

    // Attributes
    private String firstName;
    private String lastName;
    private String specialty;
    private String email;
    private String phone;
    private Date createdAt;

    // Constructor
    public Doctor(String firstName, String lastName, String specialty, String email, String phone, Date createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Method to display doctor's full name
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // Method to display the doctor's contact information
    public String getContactInfo() {
        return "Email: " + this.email + ", Phone: " + this.phone;
    }

    // Method to display doctor's details (for example, in the console)
    public void displayDoctorDetails() {
        System.out.println("Doctor ID: " + this.id);
        System.out.println("Name: " + getFullName());
        System.out.println("Specialty: " + this.specialty);
        System.out.println("Contact Info: " + getContactInfo());
        System.out.println("Created At: " + this.createdAt);
    }

    // Optional: Override the toString() method to represent the doctor object as a string
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
