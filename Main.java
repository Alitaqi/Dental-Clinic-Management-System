import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;




// Represents a patient in the hospital system
class Patient {
    private String name;
    private String gender;
    private int age;
    private String nic;
    private String contactNumber;
    private String maritalStatus;
    private String additionalDetails;

    // Constructor for initializing a new patient
    public Patient(String name, String gender, int age, String nic, String contactNumber, String maritalStatus,
            String additionalDetails) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.maritalStatus = maritalStatus;
        this.additionalDetails = additionalDetails;
    }

    // Getter methods for patient attributes
    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    // Method to update patient details
    public void updateDetails(String name, String gender, int age, String contactNumber, String maritalStatus,
            String additionalDetails) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contactNumber = contactNumber;
        this.maritalStatus = maritalStatus;
        this.additionalDetails = additionalDetails;
    }

    // Method to display patient details in a formatted manner
    public String display() {
        return String.format(
                "\n| Name: %s \n| Gender: %s \n| Age: %d \n| NIC: %s \n| Contact: %s \n| Marital Status: %s \n| Additional Details: %s",
                name, gender, age, nic, contactNumber, maritalStatus, additionalDetails);
    }
}

// Represents an appointment in the hospital system
class Appointment {
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    private String status;

    // Constructor for initializing a new appointment
    public Appointment(String patientName, String doctorName, String date, String time, String status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // Getter methods for appointment attributes
    public String getPatientName() {
        return patientName;
    }

    public String getStatus() {
        return status;
    }

    // Method to update appointment details
    public void updateDetails(String date, String time) {
        this.date = date;
        this.time = time;
    }

    // Method to update appointment status
    public void updateStatus(String status) {
        this.status = status;
    }

    // Method to display appointment details in a formatted manner
    public String display() {
        return String.format("\n| Patient: %s \n| Doctor: %s \n| Date: %s \n| Time: %s \n| Status: %s",
                patientName, doctorName, date, time, status);
    }
}

// Represents a doctor in the hospital system
class Doctor {
    private String name;
    private String specialization;
    private String availability;
    private int bookedAppointments;
    public int totalAppointments;

    // Constructor for initializing a new doctor
    public Doctor(String name, String specialization, String availability, int totalAppointments) {
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
        this.totalAppointments = totalAppointments;
        this.bookedAppointments = 0;
    }

    // Getter methods for doctor attributes
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public int getBookedAppointments() {
        return bookedAppointments;
    }

    // Method to book an appointment for the doctor
    public void bookAppointment() {
        if (bookedAppointments < totalAppointments) {
            bookedAppointments++;
        }
    }

    // Method to display doctor details in a formatted manner
    public String display() {
        return String.format(
                "\n| Name: %s \n| Specialization: %s \n| Availability: %s\n| Booked Appointments: %d\n| Total Appointments: %d",
                name, specialization, availability, bookedAppointments, totalAppointments);
    }
}

public class Main {
    private static final int MAX_PATIENTS = 100;
    private static final int MAX_APPOINTMENTS = 100;
    private static final int MAX_DOCTORS = 3;

    public static void clearScreen(){     

// System.out.print("\033[H\033[2J");  

//  System.out.flush();  
    System.out.print("\033c");


}


    
    public static char getCh()
    {  
       try {
            // Read a single character from the console
            System.out.println("Press any key to go back");
            return (char) System.in.read();
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
            return '\0'; // Return null character in case of error
        }
            
    }


    

    // Arrays to hold patients, appointments, and doctors
    private static final Patient[] patients = new Patient[MAX_PATIENTS];
    private static final Appointment[] appointments = new Appointment[MAX_APPOINTMENTS];
    private static final Doctor[] doctors = new Doctor[MAX_DOCTORS];

    private static int patientCount = 0; // Counter for patients
    private static int appointmentCount = 0; // Counter for appointments
    private static int doctorCount = 0; // Counter for doctors

    private static final Scanner scanner = new Scanner(System.in);

    // Method to prompt the user for input (String type)
    private static String promptUser(String promptMessage) {
        System.out.print(promptMessage);
        return scanner.nextLine().trim();
    }

    // Method to prompt the user for input (Integer type)
    private static int promptInt(String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                int input = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Method to prompt for a valid NIC (13 digits)
    private static String promptValidNIC(String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String nic = scanner.nextLine().trim();
            if (nic.matches("\\d{13}")) {
                return nic;
            } else {
                System.out.println("Invalid NIC. It must be exactly 13 digits.");
            }
        }
    }

    // Method to prompt for a valid contact number (11 digits)
    private static String promptValidContactNumber(String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String contactNumber = scanner.nextLine().trim();
            if (contactNumber.matches("\\d{11}")) {
                return contactNumber;
            } else {
                System.out.println("Invalid Contact Number. It must be exactly 11 digits.");
            }
        }
    }

    // Patient Management - Add a new patient
    private static void addPatient() {
        if (patientCount >= MAX_PATIENTS) {
            System.out.println("Maximum patient limit reached.");
            return;
        }

        try {
            String name = promptUser("Enter Patient Name: ");
            String gender = promptUser("Enter Gender: ");
            int age = promptInt("Enter Age: ");
            String nic = promptValidNIC("Enter NIC (13 digits): ");
            String contactNumber = promptValidContactNumber("Enter Contact Number (11 digits): ");
            String maritalStatus = promptUser("Enter Marital Status: ");
            String additionalDetails = promptUser("Enter Additional Details: ");

            patients[patientCount++] = new Patient(name, gender, age, nic, contactNumber, maritalStatus,
                    additionalDetails);
            System.out.println("Patient added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the patient: " + e.getMessage());
        }
    }

    // Patient Management - View all patients
    private static void viewPatients() {
        if (patientCount == 0) {
            System.out.println("No patients available.");
            return;
        }

        System.out.println("Patient Records:");
        for (int i = 0; i < patientCount; i++) {
            System.out.printf("%d. %s%n", i + 1, patients[i].display());
        }
        getCh();
        clearScreen();
    }

    // Patient Management - Update patient details
    private static void updatePatient() {
        if (patientCount == 0) {
            System.out.println("No patients available to update.");
            return;
        }

        try {
            String nic = promptValidNIC("Enter NIC of the patient to update: ");
            for (int i = 0; i < patientCount; i++) {
                if (patients[i].getNic().equals(nic)) {
                    String name = promptUser("Enter new Patient Name: ");
                    String gender = promptUser("Enter new Gender: ");
                    int age = promptInt("Enter new Age: ");
                    String contactNumber = promptValidContactNumber("Enter new Contact Number (11 digits): ");
                    String maritalStatus = promptUser("Enter new Marital Status: ");
                    String additionalDetails = promptUser("Enter new Additional Details: ");

                    patients[i].updateDetails(name, gender, age, contactNumber, maritalStatus, additionalDetails);
                    System.out.println("Patient details updated successfully!");
                    return;
                }
            }
            System.out.println("No patient found with the given NIC.");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the patient: " + e.getMessage());
        }
    }

    // Patient Management - Delete a patient
    private static void deletePatient() {
        if (patientCount == 0) {
            System.out.println("No patients available to delete.");
            return;
        }

        try {
            String nic = promptValidNIC("Enter NIC of the patient to delete: ");
            for (int i = 0; i < patientCount; i++) {
                if (patients[i].getNic().equals(nic)) {
                    // Shift patients array to remove the deleted patient
                    for (int j = i; j < patientCount - 1; j++) {
                        patients[j] = patients[j + 1];
                    }
                    patients[--patientCount] = null;
                    System.out.println("Patient deleted successfully!");
                    return;
                }
            }
            System.out.println("No patient found with the given NIC.");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the patient: " + e.getMessage());
        }
    }

    // Method to check if a patient exists by NIC
    private static boolean isPatientExist(String nic) {
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getNic().equals(nic)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if a doctor is available for appointments
    public static boolean isDoctorAvailable(String doctorName) {
        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i].getName().equalsIgnoreCase(doctorName)) {
                return doctors[i].getBookedAppointments() < doctors[i].totalAppointments;
            }
        }
        return false;
    }

    // Method to retrieve a patient's name using their NIC
    private static String getPatientNameByNIC(String nic) {
        for (Patient patient : patients) {
            if (patient != null && patient.getNic().equals(nic)) {
                return patient.getName();
            }
        }
        return null;
    }

    // Appointment Management - Create a new appointment
    private static void createAppointment() {
        if (appointmentCount >= MAX_APPOINTMENTS) {
            System.out.println("Maximum appointment limit reached.");
            return;
        }

        try {
            String patientNIC = promptValidNIC("Enter Patient NIC: ");
            if (!isPatientExist(patientNIC)) {
                System.out.println("Patient not found. Please add the patient first.");
                return;
            }

            String doctorName = promptUser("Enter Doctor Name: ");
            if (!isDoctorAvailable(doctorName)) {
                System.out.println("Doctor is either unavailable or fully booked.");
                return;
            }

            String date = promptUser("Enter Appointment Date (DD/MM/YYYY): ");
            String time = promptUser("Enter Appointment Time (HH:MM): ");
            String status = promptUser("Enter Appointment Status (Scheduled/Completed): ");

            String patientName = getPatientNameByNIC(patientNIC);
            appointments[appointmentCount++] = new Appointment(patientName, doctorName, date, time, status);

            // Mark doctor as having an additional booked appointment
            for (Doctor doctor : doctors) {
                if (doctor != null && doctor.getName().equals(doctorName)) {
                    doctor.bookAppointment();
                    break;
                }
            }

            System.out.println("Appointment created successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the appointment: " + e.getMessage());
        }
    }

    // Appointment Management - View all appointments
    private static void viewAppointments() {
        if (appointmentCount == 0) {
            System.out.println("No appointments available.");
            return;
        }

        System.out.println("Appointment Records:");
        for (int i = 0; i < appointmentCount; i++) {
            System.out.printf("%d. %s%n", i + 1, appointments[i].display());
        }
        getCh();
        clearScreen();
    }

    // Appointment Management - Update an appointment
    private static void updateAppointment() {
        if (appointmentCount == 0) {
            System.out.println("No appointments available to update.");
            return;
        }

        try {
            int appointmentIndex = promptInt("Enter appointment number to update: ") - 1;
            if (appointmentIndex < 0 || appointmentIndex >= appointmentCount) {
                System.out.println("Invalid appointment number.");
                return;
            }

            String date = promptUser("Enter new appointment date (DD/MM/YYYY): ");
            String time = promptUser("Enter new appointment time (HH:MM): ");

            appointments[appointmentIndex].updateDetails(date, time);
            System.out.println("Appointment updated successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while updating the appointment: " + e.getMessage());
        }
    }

    // Appointment Management - Delete an appointment
    private static void deleteAppointment() {
        if (appointmentCount == 0) {
            System.out.println("No appointments available to delete.");
            return;
        }

        try {
            int appointmentIndex = promptInt("Enter appointment number to delete: ") - 1;
            if (appointmentIndex < 0 || appointmentIndex >= appointmentCount) {
                System.out.println("Invalid appointment number.");
                return;
            }

            // Shift appointments array to remove the deleted appointment
            for (int i = appointmentIndex; i < appointmentCount - 1; i++) {
                appointments[i] = appointments[i + 1];
            }
            appointments[--appointmentCount] = null;
            System.out.println("Appointment deleted successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the appointment: " + e.getMessage());
        }
    }

    // Doctor Management - Add a new doctor
    private static void addDoctor() {
        if (doctorCount >= MAX_DOCTORS) {
            System.out.println("Maximum doctor limit reached.");
            return;
        }

        try {
            String name = promptUser("Enter Doctor Name: ");
            String specialization = promptUser("Enter Specialization: ");
            String availability = promptUser("Enter Availability: ");
            int totalAppointments = promptInt("Enter Total Appointments Available: ");

            doctors[doctorCount++] = new Doctor(name, specialization, availability, totalAppointments);
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the doctor: " + e.getMessage());
        }
    }

    // Doctor Management - View all doctors
    private static void viewDoctors() {
        if (doctorCount == 0) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("Doctor Records:");
        for (int i = 0; i < doctorCount; i++) {
            System.out.printf("%d. %s%n", i + 1, doctors[i].display());
        }
        getCh();
        clearScreen();
    }

    // Method to handle the menu and user's actions
    public static void main(String[] args) {
        while (true) {
           
            clearScreen();
            System.out.println("\n\t\t\t\t\t\t------------------Dental Care System------------------\n");
            System.out.println("\t\t\t\t\t\t\t\t1. Manage Patients");
            System.out.println("\t\t\t\t\t\t\t\t2. Manage Appointments");
            System.out.println("\t\t\t\t\t\t\t\t3. Manage Doctors");
            System.out.println("\t\t\t\t\t\t\t\t4. Exit");

            int choice = promptInt("\t\t\t\t\t\t\t\tEnter your choice: ");
            switch(choice) {
                case 1:                
                    clearScreen();
                    managePatients();
                    break;
                case 2:
                clearScreen();
                    manageAppointments();
                    break;
                case 3:
                clearScreen();
                    manageDoctors();
                    break;
                case 4:
                clearScreen();
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("\t\t\t\t\t\t\t\tInvalid choice. Please try again.");
            }
        }
    }

    // Method for managing patient-related actions
    private static void managePatients() {
        while (true) {
            
            clearScreen();
            System.out.println("\n\t\t\t\t\t\t------------------Patient Management------------------");
            System.out.println("\t\t\t\t\t\t\t\t1. Add Patient");
            System.out.println("\t\t\t\t\t\t\t\t2. View Patients");
            System.out.println("\t\t\t\t\t\t\t\t3. Update Patient");
            System.out.println("\t\t\t\t\t\t\t\t4. Delete Patient");
            System.out.println("\t\t\t\t\t\t\t\t5. Go Back");

            int choice = promptInt("\t\t\t\tEnter your choice: ");
            switch (choice) {
                case 1:

                clearScreen();    
                addPatient();
                getCh();
                clearScreen();
                    break;
                case 2:
                clearScreen();
                 viewPatients();
                    break;
                case 3:
                    clearScreen(); 
                    updatePatient();
                    break;
                case 4:
                    clearScreen(); 
                    deletePatient();
                    break;
                case 5:
                    clearScreen(); 
                    return;
                default:
                    System.out.println("\t\t\t\t\t\t\t\tInvalid choice. Please try again.");
            }
        }
    }

    // Method for managing appointment-related actions
    private static void manageAppointments() {
        while (true) {
           
            clearScreen();
            System.out.println("\n\n\t\t\t\t\t\t------------------Appointment Management------------------");
            System.out.println("\t\t\t\t\t\t\t\t1. Create Appointment");
            System.out.println("\t\t\t\t\t\t\t\t2. View Appointments");
            System.out.println("\t\t\t\t\t\t\t\t3. Update Appointment");
            System.out.println("\t\t\t\t\t\t\t\t4. Delete Appointment");
            System.out.println("\t\t\t\t\t\t\t\t5. Go Back");

            int choice = promptInt("\t\t\t\t\t\t\t\tEnter your choice: ");
            switch (choice) {
                case 1:
                    clearScreen();
                    createAppointment();
                    getCh();
                    clearScreen();
                    break;
                case 2:
                clearScreen();
                    viewAppointments();
                    break;
                case 3:
                    clearScreen();
                    updateAppointment();
                    break;
                case 4:
                    clearScreen();
                    deleteAppointment();
                    break;
                case 5:
                    clearScreen();
                    return;
                default:
                    System.out.println("\t\t\t\t\t\t\t\tInvalid choice. Please try again.");
            }
        }
    }

    // Method for managing doctor-related actions
    private static void manageDoctors() {
        while (true) {
            
            clearScreen();
            System.out.println("\n\n\t\t\t\t\t\t------------------Doctor Management------------------");
            System.out.println("\t\t\t\t\t\t\t\t1. Add Doctor");
            System.out.println("\t\t\t\t\t\t\t\t2. View Doctors");
            System.out.println("\t\t\t\t\t\t\t\t3. Go Back");

            int choice = promptInt("\t\t\t\t\t\t\t\tEnter your choice: ");
            switch(choice) {
                case 1:
                    clearScreen();
                    addDoctor();
                    getCh();
                    clearScreen();
                    break;
                case 2:
                clearScreen();
                    viewDoctors();
                    break;
                case 3:
                    clearScreen();
                    return;
                default:
                    System.out.println("\t\t\t\t\t\t\t\tInvalid choice. Please try again.");
            }
        }
    }
}