# Database Schema Design for Smart Clinic Management System

This document outlines the MySQL database schema design for the Smart Clinic Management System. The design includes the major tables necessary to manage the clinic's operations, including Doctors, Patients, Appointments, and more.

---

## Entities and Their Relationships

The database includes the following main entities (tables):

- **Doctor**
- **Patient**
- **Appointment**
- **Prescription**
- **User** (for authentication)
- **Role** (for role-based access control)

### Entity Relationships:

- A **Doctor** can have multiple **Appointments**.
- A **Patient** can book multiple **Appointments** with different doctors.
- Each **Appointment** is associated with a **Patient** and a **Doctor**.
- A **Doctor** can prescribe multiple **Prescriptions** for different **Patients**.
- **Users** are assigned roles such as **Admin**, **Doctor**, or **Patient** for authentication and authorization.

---

## Database Tables and Schema Design

### 1. **Doctor Table**

Stores information about the doctors.

```sql
CREATE TABLE Doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
````

### 2. **Patient Table**

Stores information about the patients.

```sql
CREATE TABLE Patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    dob DATE,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. **Appointment Table**

Stores information about the appointments between doctors and patients.

```sql
CREATE TABLE Appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    appointment_date DATETIME NOT NULL,
    status ENUM('Scheduled', 'Completed', 'Cancelled') DEFAULT 'Scheduled',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);
```

### 4. **Prescription Table**

Stores prescriptions issued by doctors to patients during appointments.

```sql
CREATE TABLE Prescription (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    medication_name VARCHAR(255),
    dosage VARCHAR(100),
    instructions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES Appointment(appointment_id)
);
```

### 5. **User Table**

Stores user credentials for authentication and role management.

```sql
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);
```

### 6. **Role Table**

Stores roles such as Admin, Doctor, and Patient.

```sql
CREATE TABLE Role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name ENUM('Admin', 'Doctor', 'Patient') NOT NULL
);
```

### 7. **Audit Log Table**

Stores actions performed by users for auditing purposes (e.g., who booked an appointment, who updated patient information).

```sql
CREATE TABLE AuditLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    table_name VARCHAR(100),
    record_id INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
```

---

## Example Queries

Here are some example SQL queries you can use for common operations:

### 1. **Get all appointments for a doctor**

```sql
SELECT * FROM Appointment
WHERE doctor_id = 1
ORDER BY appointment_date DESC;
```

### 2. **Get all prescriptions for a patient**

```sql
SELECT * FROM Prescription
WHERE appointment_id IN (SELECT appointment_id FROM Appointment WHERE patient_id = 1);
```

### 3. **Get all patients for a specific doctor**

```sql
SELECT DISTINCT p.first_name, p.last_name, p.email 
FROM Patient p
JOIN Appointment a ON p.patient_id = a.patient_id
WHERE a.doctor_id = 1;
```

---
