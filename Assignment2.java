package assignment;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// -----------------------------------------
// ---------- USER(BASE CLASS) -------------
// -----------------------------------------
class User{
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
 
    public User(int id, String name, String email, String password, String address) {
        this.id= id;
        this.name= name;
        this.email= email;
        this.password= password;
        this.address= address;
        }
    
    // getter methods
    public int getId(){ return id;}
    public String getName(){ return name;}
    public String getEmail(){ return email;}
    public String getPassword(){ return password;}
    public String getAddress(){ return address;}
    
    //override toString method 
      @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name +"\nemail: "+ email + "\npassword" + password+ "\naddress: "+ address; 
}
}

// -----------------------------------------
// -------- PATIENT(DERIVED CLASS)----------
// -----------------------------------------
class Patient extends User{
    private String gender;
    private ArrayList<VitalSign> vital_signs;
    private ArrayList<Appointment> appointments;
    private MedicalHistory medical_history;
    
    public Patient(int id, String name, String email, String password,String address, String gender) {
        super(id, name, email, password,address);
        this.gender = gender;
        this.vital_signs = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.medical_history =new MedicalHistory(); 
    }
    
    // getter methods 
    public String getGender(){ return gender;}
    public ArrayList<VitalSign> getVitalSign(){ return vital_signs;}
    public MedicalHistory getMedicalHistory(){ return medical_history;}
    public ArrayList<Appointment> getAppointment(){ return appointments;}
    
    // planAppointments
    public void planAppointments( Appointment appointment){
        appointments.add(appointment);
        System.out.println("\nAppointment has been planned successfully.");
    }
    // add vital signs
    public void addVitalSign(VitalSign vital_sign){
       vital_signs.add(vital_sign);
        System.out.println("\nVital Sign has been added successfully.");
    }
    
    // add feedbacks to patients medical history 
    public void addFeedbackToMedHistory(Feedback feedback){
        medical_history.addFeedback(feedback);
        
    }
    
    public void getDoctorFeedback(Feedback feedback){
        System.out.println("\nFeedback given: " + feedback.getComments());
    }
}

// -----------------------------------------
// -------- Doctor(DERIVED CLASS)----------
// -----------------------------------------
class Doctor extends User{
    private String doctorSpeciality;
    private ArrayList<Patient> patients;
    
    public Doctor(int id, String name, String email, String password,String address, String doctorSpeciality) {
        super(id, name, email, password, address);
        this.doctorSpeciality = doctorSpeciality;
        this.patients = new ArrayList<>();
    }
    
    // add patient in the patient list of doctor 
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient has been added successfully.");
    }
    
    // displaying patient data
    public void displayPatientData(Patient patient) {
        System.out.println("Patient Data for: " + patient.getName());
        System.out.println("Gender: " + patient.getGender());
         System.out.println("-----------------------------");
         for (Patient names: patients){
             System.out.println("Patients list: "+ names.getName());
         }
    }
    
        // getting vitalSigns of given patient
          public void displayPatientVitalSign(Patient patient) {
                      System.out.println("\nVital Signs:");
        for (VitalSign vitalsign : patient.getVitalSign()) {
            System.out.println("\nPulse: " + vitalsign.getPulse() +  ",\nBlood Pressure: " + vitalsign.getBloodPressure()+ "\nBMI: "+ vitalsign.getbmi()+ "\nTemperature: "+ vitalsign.getTemp()+ "\nOxygen Saturation: "+ vitalsign.getOxygenSaturation()+ "Respiratory Rate: " + vitalsign.getRespiratoryRate());
        }
    }
    
    public void provideFeedback(Patient patient ,String comments, Prescription prescription) {
       Feedback feed_back=new Feedback(this, comments, prescription);
       patient.addFeedbackToMedHistory(feed_back);
    }
    
    // Getters
    public String getDoctorSpeciality() { 
        return doctorSpeciality;
    } 
    public ArrayList<Patient> getPatients() { 
        return patients;
    } 
}

// -----------------------------------------
// ---- ADMINISTRATOR(DERIVED CLASS)--------
// -----------------------------------------
class Administrator extends User {
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private String department;
    
    public Administrator(int id, String name, String email, String password, String address,String department) {
        super(id, name, email, password,address);
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.department=department;
    }
    
    // displaying hospital details
    public void hospitalDetails() {
        System.out.println("-------Hospital Details-------:");
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Patients: " + patients.size());
    }
    
    // adding patients and doctors in the hospital system 
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient has been added successfully.");
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor has been added successfully.");
    }
    
    //toString method
    @Override
    public String toString() {
        return super.toString() + "\nDepartment: " + department;
    }
    
    // Getters
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Patient> getPatients() { return patients; }
}

// -----------------------------------------
// ----------- VITALSDATABASE---------------
// -----------------------------------------
 class VitalDataBase {
     private ArrayList<VitalSign> vital_sign;
     
     public VitalDataBase(){
         vital_sign= new ArrayList<>();
     }
     
     // adding vital sign 
     public void add_vital_sign(VitalSign vital_signs){
         vital_sign.add(vital_signs);
     }
     //getter
     public ArrayList<VitalSign> getVitalSign(){
         return vital_sign;
     }
 }


// -----------------------------------------
// ---------------- VITALSIGN---------------
// -----------------------------------------
class VitalSign{
    private double pulse ;
    private double respiratory_rate;
    private String blood_pressure;
    private double oxygen_saturation;
    private double temperature;
    private double bmi;
    
    public VitalSign(double pulse ,double respiratory_rate, String blood_pressure,  double oxygen_saturation, double temperature,double bmi){
        this.pulse=pulse ;
        this.respiratory_rate=respiratory_rate;
        this.blood_pressure=blood_pressure;
        this.oxygen_saturation=oxygen_saturation;
        this.temperature=temperature;
        this.bmi=bmi;
    }
    
    // getters
    public double getPulse(){
        return pulse;
    }
    public double getRespiratoryRate(){
        return respiratory_rate;
    }
    public double getTemp(){
        return temperature;
    }
    public String getBloodPressure(){
        return blood_pressure;
    }
    public double getOxygenSaturation(){
        return oxygen_saturation;
    }
    public double getbmi(){
        return bmi;
    }
}



// -----------------------------------------
// ----------- PRESCRIPTION---------------
// -----------------------------------------
class Prescription{
 private String medicine;
 private String frequency;
 
 public Prescription(String medicine, String frequency){
 this.medicine=medicine;
 this.frequency=frequency;
}
 
 //getters
 public String getMedicine (){
     return medicine;
 }
 public String getFrequency(){
     return frequency;
 }
}



// -----------------------------------------
// ------------------FEEDBACK---------------
// -----------------------------------------
class Feedback{
    private String comments;
    private Prescription prescription;
    private Doctor doctor;
    
    public Feedback(Doctor doctor, String comments, Prescription prescription){
        this.comments=comments;
        this.prescription=prescription;
        this.doctor=doctor;
    }
    
    //gettters
    public Doctor getDoctor(){
        return doctor; 
    }
    public Prescription getPrescription(){
        return prescription;
    }
    public String getComments(){
        return comments;
    }
}


// -----------------------------------------
// ----------MEDICAL-HISTORY----------------
// -----------------------------------------
class MedicalHistory{
    private ArrayList<Feedback> feed_back;
    
    public MedicalHistory(){
        feed_back=new ArrayList<>();
    }
    
    // Method to add feedback
    public void addFeedback(Feedback feedbacks) {
        feed_back.add(feedbacks);
    }
    
//getter
    public ArrayList<Feedback> getFeedback(){
        return feed_back;
    }
}


// -----------------------------------------
// ----------APPOINTMENT--------------------
// -----------------------------------------
class Appointment{
    private String availability;
    private Doctor doctor;
    private Patient patient;
  
    public Appointment(String availability, Patient patient, Doctor doctor){
        this.availability=availability;
        this.doctor=doctor;
        this.patient=patient;
    }
    
    //method to show appointment details
    public void showAppointmentDetails(){
        System.out.println("------Appointment details-------");
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Doctor Name: " + doctor.getName());
        System.out.println("Availability: "+ availability);
    }
    
    //GETTERS
    public String getAvailability(){
        return availability;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public Patient getPatient (){
        return patient;
    }
}


// -----------------------------------------
// -------APPOINTMENT-SCHEDULING------------
// -----------------------------------------
class AppointmentManager{
    private ArrayList<Appointment> appointment;

    public AppointmentManager(){
        appointment=new ArrayList<>();
    }

 public void bookAppointment(Appointment appointments) {
        appointment.add(appointments);
        System.out.println("Appointment has been booked successfully.");
    }

 public void cancelAppointment(Appointment appointments){
     appointment.remove(appointments);
}
}

// -----------------------------------------
// ----------MAIN METHOD--------------------
// -----------------------------------------
public class Assignment {
    private static Scanner scanner = new Scanner(System.in);
    private static Administrator admin = new Administrator(1, "Admin", "admin@hospital.com", "admin123", "Main Office", "Management");

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n***********Hospital System***********");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Record Vital Signs");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Give Feedback");
            System.out.println("6. View Patient History");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch(choice) {
                case 1: 
                    addPatient(); break;
                case 2:
                    addDoctor(); break;
                case 3:
                    recordVitalSigns(); break;
                case 4:
                    scheduleAppointment(); break;
                case 5:
                    giveFeedback(); break;
                case 6:
                    viewPatientHistory();break;
                case 7: 
                    System.out.println("Exiting system...");
                    return;
                default: 
                    System.out.println("Invalid choice!");
            }
        }
    }

    //---------func to add patient---------
    private static void addPatient() {
        System.out.println("--------------------------------------");
        System.out.println("             ADDING PATIENT           ");
        System.out.println("--------------------------------------");
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
          System.out.print("Enter email: ");
        String email = scanner.nextLine();
          System.out.print("Enter password: ");
        String password = scanner.nextLine();
          System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        
        Patient patient = new Patient(id, name, email, password, address, gender);
        admin.addPatient(patient);
        System.out.println("----------------------------------------");
    }

    
    //---------func to display patient history---------
    private static void viewPatientHistory() {
    Patient patient = findPatient();
    if (patient == null) return;

    ArrayList<Feedback> feedbackList = patient.getMedicalHistory().getFeedback();
    for (Feedback fb : feedbackList) {
        
        System.out.println("--------------------------------------");
        System.out.println("      Displaying Patient History      ");
        System.out.println("--------------------------------------");
        System.out.println("Doctor: " + fb.getDoctor().getName());
        System.out.println("Comments: " + fb.getComments());
        System.out.println("Prescription: " + fb.getPrescription().getMedicine());
        System.out.println("--------------------------------------");
    }
}

    // -----------func to add doctor-----------
    private static void addDoctor() {
        System.out.println("--------------------------------------");
        System.out.println("            ADDING DOCTOR             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
         System.out.print("Enter password: ");
        String password = scanner.nextLine();
          System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter specialty: ");
        String specialty = scanner.nextLine();
        
        Doctor doctor = new Doctor(id, name, email,password,address, specialty);
        admin.addDoctor(doctor);
        System.out.println("----------------------------------------");
    }

    //---------- func to record vital signs of patient------------
    private static void recordVitalSigns() {
      
        Patient patient = findPatient();
        if(patient == null) return;
        System.out.println("--------------------------------------");
        System.out.println("            ADDING VITALS             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter pulse: ");
        double pulse = scanner.nextDouble();
         System.out.print("Enter respiratory rate: ");
        double resp = scanner.nextDouble();
         System.out.print("Enter Oxygen Saturation: ");
        double o2s = scanner.nextDouble();
         System.out.print("Enter temperature: ");
        double temp = scanner.nextDouble();
        System.out.print("Enter blood pressure:");
        String bp = scanner.next();
         System.out.print("Enter BMI: ");
        double bmi = scanner.nextDouble();
        
        VitalSign vs = new VitalSign(pulse, resp, bp, o2s, temp, bmi);
        patient.addVitalSign(vs);
        System.out.println("Vitals have been recorded!");
        System.out.println("----------------------------------------");
    }

    //---------func to schedule an appointmnet---------
    private static void scheduleAppointment() {
       System.out.println("--------------------------------------");
         System.out.println("          Booking Appointmnet         ");
         System.out.println("--------------------------------------");
        Patient patient = findPatient();
        if(patient == null) return;
        Doctor doctor = findDoctor();
        if(doctor == null) return;
        
        System.out.print("Enter date/time: ");
        String datetime = scanner.nextLine();
        
        Appointment appt = new Appointment(datetime, patient, doctor);
        patient.planAppointments(appt);
         System.out.println("----------------------------------------");
    }

    //---------func to give feedback---------
    private static void giveFeedback() {
       
        Doctor doctor = findDoctor();
        if(doctor == null) return;


        Patient patient = findPatient();
        if(patient == null) return;
        System.out.println("--------------------------------------");
        System.out.println("          GIVING FEEDBACK             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter comments: ");
        String comments = scanner.nextLine();
        System.out.print("Enter intake frequency:(once/twicw/thrice) ");
        String freq = scanner.nextLine();
        System.out.print("Enter medication: ");
        String med = scanner.nextLine();
        
        Prescription pres = new Prescription(med, freq);
        doctor.provideFeedback(patient, comments, pres);
        System.out.println("Feedback has been given!");
     System.out.println("----------------------------------------");   
    }

    //---------func to find patient using id---------
    private static Patient findPatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Patient p : admin.getPatients()) {
            if(p.getId() == id) return p;
        }
        System.out.println("Patient not found!");
        return null;
    }

    //---------func to find doctor using id---------
    private static Doctor findDoctor() {
        System.out.print("Enter doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Doctor d : admin.getDoctors()) {
            if(d.getId() == id) return d;
        }
        System.out.println("Doctor not found!");
        return null;
    }
}
-----------------------------
------------
-------------
--------------
----------------
----------------------------
------------
-------------
--------------
----------------
----------------------------
------------
-------------
--------------
----------------
----------------------------
------------
-------------
--------------
----------------
----------------------------
------------
-------------
--------------
----------------
----------------------------
------------
-------------
--------------
----------------
package labs;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
// -----------------------------------------
// ---------- USER(BASE CLASS) -------------



class User{   
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
 
    public User(int id, String name, String email, String password, String address, String phoneNumber) {
        this.id= id;
        this.name= name;
        this.email= email;
        this.password= password;
        this.address= address;
        this.phoneNumber=phoneNumber;
        }
    
    // getter methods
    public int getId(){ return id;}
    public String getName(){ return name;}
    public String getEmail(){ return email;}
    public String getPassword(){ return password;}
    public String getAddress(){ return address;}
    public String getPhone(){ return phoneNumber;}
    
    //override toString method 
      @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name +"\nemail: "+ email + "\npassword" + password+ "\naddress: "+ address + "\nPhoneNumber: "+ phoneNumber; 
}
}

// -----------------------------------------
// -------- PATIENT(DERIVED CLASS)----------
// -----------------------------------------
class Patient extends User{
    private String gender;
    private String emergencyContact;
    private ArrayList<VitalSign> vital_signs;
    private ArrayList<Appointment> appointments;
    private MedicalHistory medical_history;
    
    public Patient(int id, String name, String email, String password,String address, String gender, String phoneNumber, String emergencyContact) {
        super(id, name, email, password,address,phoneNumber);
        this.gender = gender;
        this.emergencyContact=emergencyContact;
        this.vital_signs = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.medical_history =new MedicalHistory(); 
    }
    
    // getter methods 
    public String getGender(){ return gender;}
    public String getEmergencyContact(){ return emergencyContact;}
    public ArrayList<VitalSign> getVitalSign(){ return vital_signs;}
    public MedicalHistory getMedicalHistory(){ return medical_history;}
    public ArrayList<Appointment> getAppointment(){ return appointments;}
    
    // planAppointments
    public void planAppointments( Appointment appointment){
        appointments.add(appointment);
        System.out.println("\nAppointment has been planned successfully.");
    }
    // add vital signs
    public void addVitalSign(VitalSign vital_sign){
       vital_signs.add(vital_sign);
        System.out.println("\nVital Sign has been added successfully.");
    }
    
    // add feedbacks to patients medical history 
    public void addFeedbackToMedHistory(Feedback feedback){
        medical_history.addFeedback(feedback);
        
    }
    
    public void getDoctorFeedback(Feedback feedback){
        System.out.println("\nFeedback given: " + feedback.getComments());
    }
}

// -----------------------------------------
// -------- Doctor(DERIVED CLASS)----------
// -----------------------------------------
class Doctor extends User{
    private String doctorSpeciality;
    private ArrayList<Patient> patients;
    
    public Doctor(int id, String name, String email, String password,String address, String doctorSpeciality, String phoneNumber) {
        super(id, name, email, password, address, phoneNumber);
        this.doctorSpeciality = doctorSpeciality;
        this.patients = new ArrayList<>();
    }
    
    // add patient in the patient list of doctor 
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient has been added successfully.");
    }
    
    // displaying patient data
    public void displayPatientData(Patient patient) {
        System.out.println("Patient Data for: " + patient.getName());
        System.out.println("Gender: " + patient.getGender());
         System.out.println("-----------------------------");
         for (Patient names: patients){
             System.out.println("Patients list: "+ names.getName());
         }
    }
    
        // getting vitalSigns of given patient
          public void displayPatientVitalSign(Patient patient) {
                      System.out.println("\nVital Signs:");
        for (VitalSign vitalsign : patient.getVitalSign()) {
            System.out.println("\nPulse: " + vitalsign.getPulse() +  ",\nBlood Pressure: " + vitalsign.getBloodPressure()+ "\nBMI: "+ vitalsign.getbmi()+ "\nTemperature: "+ vitalsign.getTemp()+ "\nOxygen Saturation: "+ vitalsign.getOxygenSaturation()+ "Respiratory Rate: " + vitalsign.getRespiratoryRate());
        }
    }
    
    public void provideFeedback(Patient patient ,String comments, Prescription prescription) {
       Feedback feed_back=new Feedback(this, comments, prescription);
       patient.addFeedbackToMedHistory(feed_back);
    }
    
    // Getters
    public String getDoctorSpeciality() { 
        return doctorSpeciality;
    } 
    public ArrayList<Patient> getPatients() { 
        return patients;
    } 
}

// -----------------------------------------
// ---- ADMINISTRATOR(DERIVED CLASS)--------
// -----------------------------------------
class Administrator extends User {
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private String department;
    
    public Administrator(int id, String name, String email, String password, String address,String department, String phoneNumber) {
        super(id, name, email, password,address, phoneNumber);
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.department=department;
    }
    
    // displaying hospital details
    public void hospitalDetails() {
        System.out.println("-------Hospital Details-------:");
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Patients: " + patients.size());
    }
    
    // adding patients and doctors in the hospital system 
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient has been added successfully.");
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor has been added successfully.");
    }
    
    //toString method
    @Override
    public String toString() {
        return super.toString() + "\nDepartment: " + department;
    }
    
    // Getters
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Patient> getPatients() { return patients; }
}

// -----------------------------------------
// ----------- VITALSDATABASE---------------
// -----------------------------------------
 class VitalDataBase {
     private ArrayList<VitalSign> vital_sign;
     
     public VitalDataBase(){
         vital_sign= new ArrayList<>();
     }
     
     // adding vital sign 
     public void add_vital_sign(VitalSign vital_signs){
         vital_sign.add(vital_signs);
     }
     //getter
     public ArrayList<VitalSign> getVitalSign(){
         return vital_sign;
     }
 }


// -----------------------------------------
// ---------------- VITALSIGN---------------
// -----------------------------------------
class VitalSign{
    private double pulse ;
    private double respiratory_rate;
    private String blood_pressure;
    private double oxygen_saturation;
    private double temperature;
    private double bmi;
    
    public VitalSign(double pulse ,double respiratory_rate, String blood_pressure,  double oxygen_saturation, double temperature,double bmi){
        this.pulse=pulse ;
        this.respiratory_rate=respiratory_rate;
        this.blood_pressure=blood_pressure;
        this.oxygen_saturation=oxygen_saturation;
        this.temperature=temperature;
        this.bmi=bmi;
    }
    
    // getters
    public double getPulse(){
        return pulse;
    }
    public double getRespiratoryRate(){
        return respiratory_rate;
    }
    public double getTemp(){
        return temperature;
    }
    public String getBloodPressure(){
        return blood_pressure;
    }
    public double getOxygenSaturation(){
        return oxygen_saturation;
    }
    public double getbmi(){
        return bmi;
    }
}



// -----------------------------------------
// ----------- PRESCRIPTION---------------
// -----------------------------------------
class Prescription{
 private String medicine;
 private String frequency;
 
 public Prescription(String medicine, String frequency){
 this.medicine=medicine;
 this.frequency=frequency;
}
 
 //getters
 public String getMedicine (){
     return medicine;
 }
 public String getFrequency(){
     return frequency;
 }
}



// -----------------------------------------
// ------------------FEEDBACK---------------
// -----------------------------------------
class Feedback{
    private String comments;
    private Prescription prescription;
    private Doctor doctor;
    
    public Feedback(Doctor doctor, String comments, Prescription prescription){
        this.comments=comments;
        this.prescription=prescription;
        this.doctor=doctor;
    }
    
    //gettters
    public Doctor getDoctor(){
        return doctor; 
    }
    public Prescription getPrescription(){
        return prescription;
    }
    public String getComments(){
        return comments;
    }
}


// -----------------------------------------
// ----------MEDICAL-HISTORY----------------
// -----------------------------------------
class MedicalHistory{
    private ArrayList<Feedback> feed_back;
    
    public MedicalHistory(){
        feed_back=new ArrayList<>();
    }
    
    // Method to add feedback
    public void addFeedback(Feedback feedbacks) {
        feed_back.add(feedbacks);
    }
    
//getter
    public ArrayList<Feedback> getFeedback(){
        return feed_back;
    }
}


// -----------------------------------------
// ----------APPOINTMENT--------------------
// -----------------------------------------
class Appointment{
    private String availability;
    private Doctor doctor;
    private Patient patient;
  
    public Appointment(String availability, Patient patient, Doctor doctor){
        this.availability=availability;
        this.doctor=doctor;
        this.patient=patient;
    }
    
    //method to show appointment details
    public void showAppointmentDetails(){
        System.out.println("------Appointment details-------");
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Doctor Name: " + doctor.getName());
        System.out.println("Availability: "+ availability);
    }
    
    //GETTERS
    public String getAvailability(){
        return availability;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public Patient getPatient (){
        return patient;
    }
}


// -----------------------------------------
// -------APPOINTMENT-SCHEDULING------------
// -----------------------------------------
class AppointmentManager{
    private ArrayList<Appointment> appointment;

    public AppointmentManager(){
        appointment=new ArrayList<>();
    }

 public void bookAppointment(Appointment appointments) {
        appointment.add(appointments);
        System.out.println("Appointment has been booked successfully.");
    }

 public void cancelAppointment(Appointment appointments){
     appointment.remove(appointments);
}
}



// -----------------------------------------
// ----------module 2--------------------
// -----------------------------------------
class ChatServer {
    private ArrayList<String> messages;

    public ChatServer() {
        messages = new ArrayList<>();
    }

    public void broadcastMessage(String sender, String message) {
        String fullMessage = sender + ": " + message;
        messages.add(fullMessage);
        System.out.println(fullMessage); // simulate real-time chat
    }

    public ArrayList<String> getChatHistory() {
        return messages;
    }
}

 class VideoCall {
    public static void startCall(Patient patient, Doctor doctor) {
        String uniqueID = UUID.randomUUID().toString().substring(0, 8);
        String link = "https://meet.google.com/" + uniqueID;
        System.out.println("\n🔗 Video Call Link:");
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Link: " + link);
    }
}



class ChatClient {
    private String userName;
    private String role;
    private ChatServer server;

    public ChatClient(String userName, String role, ChatServer server) {
        this.userName = userName;
        this.role = role;
        this.server = server;
    }

    public void sendMessage(String message) {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        server.broadcastMessage("[" + timeStamp + "] " + role + " " + userName, message);
    }

public void startChatWith(ChatClient otherClient) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("📡 Chat started between " + this.role + " " + this.userName + " and " + otherClient.role + " " + otherClient.userName);
    System.out.println("Type 'exit' during your turn to end the chat.\n");

    boolean isMyTurn = true;

    while (true) {
        System.out.println("\n--- Chat History ---");
        for (String msg : server.getChatHistory()) {
            System.out.println(msg);
        }

        ChatClient current = isMyTurn ? this : otherClient;
        System.out.print(current.role + " " + current.userName + " > ");
        String msg = scanner.nextLine();

        if (msg.equalsIgnoreCase("exit")) {
            System.out.println(current.userName + " has ended the chat.");
            break;
        }

        current.sendMessage(msg);
        isMyTurn = !isMyTurn; // switch turns
    }
}

}

// -----------------------------------------
// ----------MODULE 1-------------------
// -----------------------------------------
class NotificationService {

    // Alert to doctor
    public void sendAlertToDoctor(String message, Doctor doctor) {
        System.out.println("\n=====================================");
        System.out.println("      EMERGENCY ALERT - DOCTOR");
        System.out.println("-------------------------------------");
        System.out.println("To      : Dr. " + doctor.getName());
        System.out.println("Message : " + message);
        System.out.println("Status  : Alert sent to Doctor");
        System.out.println("=====================================\n");
    }

    // Alert to emergency contact
    public void sendAlertToEmergencyContact(String message, String contact) {
        System.out.println("\n=====================================");
        System.out.println("      EMERGENCY ALERT - CONTACT");
        System.out.println("-------------------------------------");
        System.out.println("To      : " + contact);
        System.out.println("Message : " + message);
        System.out.println("Status  : Alert sent to Emergency Contact");
        System.out.println("=====================================\n");
    }
}


class EmergencyAlert {
    private NotificationService notifier;

    public EmergencyAlert(NotificationService notifier) {
        this.notifier = notifier;
    }

    public void checkVitals(Patient patient) {
        for (VitalSign vs : patient.getVitalSign()) {
            boolean critical = false;
            StringBuilder msg = new StringBuilder("CRITICAL VITALS for " + patient.getName() + ":\n");

            // Check each vital
            if (vs.getPulse() < 50 || vs.getPulse() > 120) {
                msg.append("- Pulse: ").append(vs.getPulse()).append("\n");
                critical = true;
            }
            if (vs.getRespiratoryRate() < 12 || vs.getRespiratoryRate() > 25) {
                msg.append("- Respiratory Rate: ").append(vs.getRespiratoryRate()).append("\n");
                critical = true;
            }
            if (vs.getTemp() > 103 || vs.getTemp() < 95) {
                msg.append("- Temperature: ").append(vs.getTemp()).append("°F\n");
                critical = true;
            }
            if (vs.getOxygenSaturation() < 90) {
                msg.append("- Oxygen Saturation: ").append(vs.getOxygenSaturation()).append("%\n");
                critical = true;
            }
            if (vs.getbmi() > 40 || vs.getbmi() < 15) {
                msg.append("- BMI: ").append(vs.getbmi()).append("\n");
                critical = true;
            }
            if (!vs.getBloodPressure().equalsIgnoreCase("120/80")) { // basic check
                msg.append("- Blood Pressure: ").append(vs.getBloodPressure()).append("\n");
                critical = true;
            }

            if (critical) {
                Doctor doctor = findAssignedDoctor(patient);
                if (doctor != null) {
                    notifier.sendAlertToDoctor(msg.toString(), doctor);
                } else {
                    System.out.println("No doctor assigned. Sending alert to duty doctor.");
                }

                // Send to emergency contact too
                notifier.sendAlertToEmergencyContact(msg.toString(), patient.getEmergencyContact());
                return;
            }
        }

        System.out.println("Vitals are within normal range.");
    }

    private Doctor findAssignedDoctor(Patient patient) {
        ArrayList<Appointment> appts = patient.getAppointment();
        if (appts.isEmpty()) return null;
        return appts.get(appts.size() - 1).getDoctor(); // Latest assigned doctor
    }
}


class PanicButton {
    private NotificationService notifier;

    public PanicButton(NotificationService notifier) {
        this.notifier = notifier;
    }

    public void trigger(Patient patient) {
        String message = "PANIC BUTTON TRIGGERED by " + patient.getName() + 
                         ". Immediate attention is required.";

        Doctor doctor = getAssignedDoctor(patient);

        if (doctor != null) {
            notifier.sendAlertToDoctor(message, doctor);
        } else {
            System.out.println("No doctor assigned to patient. Alert skipped for doctor.");
        }

        // Always notify emergency contact
        notifier.sendAlertToEmergencyContact(message, patient.getEmergencyContact());
    }

    private Doctor getAssignedDoctor(Patient patient) {
        ArrayList<Appointment> appts = patient.getAppointment();
        if (appts.isEmpty()) return null;
        return appts.get(appts.size() - 1).getDoctor();
    }
}




// -----------------------------------------
// ----------MAIN METHOD--------------------
// -----------------------------------------
interface Notifiable {
    void sendNotification(String message);
}

class EmailNotification implements Notifiable {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
public void sendNotification(String message) {
    System.out.println("\n=====================================");
    System.out.println("          EMAIL NOTIFICATION");
    System.out.println("-------------------------------------");
    System.out.println("To      : " + email);
    System.out.println("Message : " + message);
    System.out.println("Status  :    Sent Successfully");
    System.out.println("=====================================\n");
}

}

class SMSNotification implements Notifiable {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
public void sendNotification(String message) {
    System.out.println("\n=====================================");
    System.out.println("          SMS NOTIFICATION");
    System.out.println("-------------------------------------");
    System.out.println("To      : " + phoneNumber);
    System.out.println("Message : " + message);
    System.out.println("Status  :    Sent Successfully");
    System.out.println("=====================================\n");
}

}



class ReminderService {
    private ArrayList<Notifiable> channels;

    public ReminderService() {
        channels = new ArrayList<>();
    }

    public void addChannel(Notifiable channel) {
        channels.add(channel);
    }

    public void sendReminders(String message) {
        for (Notifiable channel : channels) {
            channel.sendNotification(message);
        }
    }
}


// -----------------------------------------
// ----------MAIN METHOD--------------------
// -----------------------------------------
public class Labs {
    private static Scanner scanner = new Scanner(System.in);
    private static Administrator admin = new Administrator(1, "Admin", "admin@hospital.com", "admin123", "Main Office", "Management", "033-00000000");
private static NotificationService notifier = new NotificationService();
private static EmergencyAlert emergencyAlert = new EmergencyAlert(notifier);
private static PanicButton panicButton = new PanicButton(notifier);

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n***********Hospital System***********");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Record Vital Signs");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Give Feedback");
            System.out.println("6. View Patient History");
            System.out.println("8. Start Doctor-Patient Chat");
            System.out.println("9. Generate Video Call Link");
            System.out.println("1O. Check for Emergency Vitals");
            System.out.println("11. Trigger Panic Button");
            System.out.println("12. Send Manual Reminder to Patient");
            System.out.println("13. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch(choice) {
                case 1: 
                    addPatient(); break;
                case 2:
                    addDoctor(); break;
                case 3:
                    recordVitalSigns(); break;
                case 4:
                    scheduleAppointment(); break;
                case 5:
                    giveFeedback(); break;
                case 6:
                    viewPatientHistory();break;
                case 7: 
                    System.out.println("Exiting system...");
                    return;
                case 8: 
                    Patient chatPatient = findPatient();
                    Doctor chatDoctor = findDoctor();
                    if (chatPatient != null && chatDoctor != null) {
                    ChatServer server = new ChatServer();
                    ChatClient doctorClient = new ChatClient(chatDoctor.getName(), "Dr.", server);
                    ChatClient patientClient = new ChatClient(chatPatient.getName(), "Patient", server);

                    doctorClient.startChatWith(patientClient);
                    }
                    break;
                case 9:
                   Patient vcPatient = findPatient();
                   Doctor vcDoctor = findDoctor();
                   if (vcPatient != null && vcDoctor != null) {
                   VideoCall.startCall(vcPatient, vcDoctor);
                   }
                   break;
                   case 10:
    Patient patientToCheck = findPatient();
    if (patientToCheck != null) {
        emergencyAlert.checkVitals(patientToCheck);
    }
    break;

case 11:
    Patient panicPatient = findPatient();
    if (panicPatient != null) {
        panicButton.trigger(panicPatient);
    }
    break;

    case 12:
    sendManualReminder();
    break;

                default: 
                    System.out.println("Invalid choice!");
            }
        }
    }

    //---------func to add patient---------
    private static void addPatient() {
        System.out.println("--------------------------------------");
        System.out.println("             ADDING PATIENT           ");
        System.out.println("--------------------------------------");
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
          System.out.print("Enter email: ");
        String email = scanner.nextLine();
          System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Emergency contact (Name + Contact Information: ");
        String EmergencyNumber = scanner.nextLine();
          System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        
        Patient patient = new Patient(id, name, email, password, address, gender, phoneNumber, EmergencyNumber);
        admin.addPatient(patient);
        System.out.println("----------------------------------------");
    }

    
    //---------func to display patient history---------
    private static void viewPatientHistory() {
    Patient patient = findPatient();
    if (patient == null) return;

    ArrayList<Feedback> feedbackList = patient.getMedicalHistory().getFeedback();
    for (Feedback fb : feedbackList) {
        
        System.out.println("--------------------------------------");
        System.out.println("      Displaying Patient History      ");
        System.out.println("--------------------------------------");
        System.out.println("Doctor: " + fb.getDoctor().getName());
        System.out.println("Comments: " + fb.getComments());
        System.out.println("Prescription: " + fb.getPrescription().getMedicine());
        System.out.println("--------------------------------------");
    }
}

    // -----------func to add doctor-----------
    private static void addDoctor() {
        System.out.println("--------------------------------------");
        System.out.println("            ADDING DOCTOR             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
         System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
          System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter specialty: ");
        String specialty = scanner.nextLine();
        
        Doctor doctor = new Doctor(id, name, email,password,address, specialty, phoneNumber);
        admin.addDoctor(doctor);
        System.out.println("----------------------------------------");
    }

    //---------- func to record vital signs of patient------------
    private static void recordVitalSigns() {
      
        Patient patient = findPatient();
        if(patient == null) return;
        System.out.println("--------------------------------------");
        System.out.println("            ADDING VITALS             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter pulse: ");
        double pulse = scanner.nextDouble();
         System.out.print("Enter respiratory rate: ");
        double resp = scanner.nextDouble();
         System.out.print("Enter Oxygen Saturation: ");
        double o2s = scanner.nextDouble();
         System.out.print("Enter temperature: ");
        double temp = scanner.nextDouble();
        System.out.print("Enter blood pressure:");
        String bp = scanner.next();
         System.out.print("Enter BMI: ");
        double bmi = scanner.nextDouble();
        
        VitalSign vs = new VitalSign(pulse, resp, bp, o2s, temp, bmi);
        patient.addVitalSign(vs);
        System.out.println("Vitals have been recorded!");
        System.out.println("----------------------------------------");
    }

    //---------func to schedule an appointmnet---------
    private static void scheduleAppointment() {
       System.out.println("--------------------------------------");
         System.out.println("          Booking Appointmnet         ");
         System.out.println("--------------------------------------");
        Patient patient = findPatient();
        if(patient == null) return;
        Doctor doctor = findDoctor();
        if(doctor == null) return;
        
        System.out.print("Enter date/time: ");
        String datetime = scanner.nextLine();
        
        Appointment appt = new Appointment(datetime, patient, doctor);
        patient.planAppointments(appt);
        ReminderService reminder = new ReminderService();
reminder.addChannel(new EmailNotification(patient.getEmail()));
reminder.addChannel(new SMSNotification(patient.getPhone())); // Replace with real input if needed

String message = "Reminder: You have an appointment with Dr. " +
                 doctor.getName() + " at " + datetime;

reminder.sendReminders(message);

         System.out.println("----------------------------------------");
    }

    //---------func to give feedback---------
    private static void giveFeedback() {
       
        Doctor doctor = findDoctor();
        if(doctor == null) return;


        Patient patient = findPatient();
        if(patient == null) return;
        System.out.println("--------------------------------------");
        System.out.println("          GIVING FEEDBACK             ");
        System.out.println("--------------------------------------");
        System.out.print("Enter comments: ");
        String comments = scanner.nextLine();
        System.out.print("Enter intake frequency:(once/twicw/thrice) ");
        String freq = scanner.nextLine();
        System.out.print("Enter medication: ");
        String med = scanner.nextLine();
        
        Prescription pres = new Prescription(med, freq);
        doctor.provideFeedback(patient, comments, pres);
        System.out.println("Feedback has been given!");
     System.out.println("----------------------------------------");   
    }

    //---------func to find patient using id---------
    private static Patient findPatient() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Patient p : admin.getPatients()) {
            if(p.getId() == id) return p;
        }
        System.out.println("Patient not found!");
        return null;
    }

    //---------func to find doctor using id---------
    private static Doctor findDoctor() {
        System.out.print("Enter doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for(Doctor d : admin.getDoctors()) {
            if(d.getId() == id) return d;
        }
        System.out.println("Doctor not found!");
        return null;
    }
    
    //-----------------Manual Reminders--------------------------
    private static void sendManualReminder() {
    Patient patient = findPatient();  // Reuse your patient search
    if (patient == null) return;

    ReminderService reminder = new ReminderService();
    reminder.addChannel(new EmailNotification(patient.getEmail()));
    reminder.addChannel(new SMSNotification(patient.getPhone()));

    System.out.print("Enter reminder (medication/appointment): ");
    String message = scanner.nextLine();

    reminder.sendReminders(message);

}

}
