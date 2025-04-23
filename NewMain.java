package Myfinal;



import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

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
    }
    // add vital signs
    public void addVitalSign(VitalSign vital_sign){
       vital_signs.add(vital_sign);
    }
    
    // add feedbacks to patients medical history 
    public void addFeedbackToMedHistory(Feedback feedback){
        medical_history.addFeedback(feedback);
        
    }
    
    public void getDoctorFeedback(Feedback feedback){
        System.out.println("\nFeedback given: " + feedback.getComments());
    }
    
    public void cancelAppointments( Integer appointmentNumber){
        appointments.remove(appointmentNumber);
        System.out.println("appointment removed");
        
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
    if (!patients.contains(patient)) {
        patients.add(patient);
    } else {
        System.out.println("Patient already exists in the doctor's list.");
    }
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
        
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
     
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
// ----------------------------------------
class Appointment {
    private Date appointmentDate; // new field
    private Doctor doctor;
    private Patient patient;
    private boolean reminderSent = false;

    public Appointment(Date appointmentDate, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        
    }

    public void showAppointmentDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        System.out.println("------Appointment details-------");
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Doctor Name: " + doctor.getName());
        System.out.println("Appointment Date: " + sdf.format(appointmentDate));
    }

     public boolean isReminderSent() {
        return reminderSent;
    }
    
    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
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
    }

 public void cancelAppointment(Appointment appointments){
     appointment.remove(appointments);
}
 public ArrayList<Appointment> getAppointments(){
     return appointment;
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

        System.out.println("\n=====================================");
        System.out.println("     VIDEO CONSULTATION SESSION");
        System.out.println("-------------------------------------");
        System.out.println("Patient : " + patient.getName());
        System.out.println("Doctor  : Dr. " + doctor.getName());
        System.out.println("Link    : " + link);
        System.out.println("Status     : Call Created Successfully");
        System.out.println("=====================================\n");
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
        String sender = String.format("%-16s", role + " " + userName); // padded to 16 chars
        server.broadcastMessage("[" + timeStamp + "]     " + sender, message);
    }

    public void startChatWith(ChatClient otherClient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chat started between " + this.role + " " + this.userName + " and " + otherClient.role + " " + otherClient.userName);
        System.out.println("Type 'exit' during your turn to end the chat.\n");

        boolean isMyTurn = true;

        while (true) {
            clearConsole();
            System.out.println("\n--------- Chat History ---------");
            for (String msg : server.getChatHistory()) {
                System.out.println(msg);
            }

            ChatClient current = isMyTurn ? this : otherClient;
            String prompt = String.format("%-16s > ", current.role + " " + current.userName); // prompt formatting
            System.out.print(prompt);
            String msg = scanner.nextLine();

            if (msg.equalsIgnoreCase("exit")) {
                System.out.println(current.userName + " has ended the chat.");
                break;
            }

            current.sendMessage(msg);
            isMyTurn = !isMyTurn;
        }
    }

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}

// -----------------------------------------
// ----------MODULE 1-------------------
// -----------------------------------------
class NotificationService {

    // Alert to doctor
    public void sendAlertToDoctor(String message, Doctor doctor) {
        System.out.println("\n============================================");
        System.out.println("          EMERGENCY ALERT - DOCTOR");
        System.out.println("----------------------------------------------");
        System.out.println("To      : Dr. " + doctor.getName());
        System.out.println("Message : " + message);
        System.out.println("Status  : Alert sent to Doctor");
        System.out.println("============================================\n");
    }

    // Alert to emergency contact
    public void sendAlertToEmergencyContact(String message, String contact) {
        System.out.println("\n============================================");
        System.out.println("    EMERGENCY ALERT - EMERGENCY CONTACT");
        System.out.println("----------------------------------------------");
        System.out.println("To      : " + contact);
        System.out.println("Message : " + message);
        System.out.println("Status  : Alert sent to Emergency Contact");
        System.out.println("============================================\n");
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
            StringBuilder alertBody = new StringBuilder();

            // Check each vital
            if (vs.getPulse() < 50 || vs.getPulse() > 120) {
                msg.append("- Pulse: ").append(vs.getPulse()).append("\n");
                alertBody.append("- Pulse: <b>").append(vs.getPulse()).append("</b><br>");
                critical = true;
            }
            if (vs.getRespiratoryRate() < 12 || vs.getRespiratoryRate() > 25) {
                msg.append("- Respiratory Rate: ").append(vs.getRespiratoryRate()).append("\n");
                alertBody.append("- Respiratory Rate: <b>").append(vs.getRespiratoryRate()).append("</b><br>");
                critical = true;
            }
            if (vs.getTemp() > 103 || vs.getTemp() < 95) {
                msg.append("- Temperature: ").append(vs.getTemp()).append("°F\n");
                alertBody.append("- Temperature: <b>").append(vs.getTemp()).append("°C</b><br>");
                critical = true;
            }
            if (vs.getOxygenSaturation() < 90) {
                msg.append("- Oxygen Saturation: ").append(vs.getOxygenSaturation()).append("%\n");
                alertBody.append("- Oxygen Saturation: <b>").append(vs.getOxygenSaturation()).append("%</b><br>");
                critical = true;
            }
            if (vs.getbmi() > 40 || vs.getbmi() < 15) {
                msg.append("- BMI: ").append(vs.getbmi()).append("\n");
                alertBody.append("- BMI: <b>").append(vs.getbmi()).append("</b><br>");
                critical = true;
            }
            if (!vs.getBloodPressure().equalsIgnoreCase("120/80")) { // basic check
                msg.append("- Blood Pressure: ").append(vs.getBloodPressure()).append("\n");
                alertBody.append("- Blood Pressure: <b>").append(vs.getBloodPressure()).append("</b><br>");
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
            
            // If any vital is critical, send email
// Send email if anything critical
if (critical) {
    JavaEmailSender emailSender = new JavaEmailSender();
    emailSender.createAndSendEmail(
        patient.getEmail(),    // ✅ send to patient
        "⚠️ Critical Alert: Your Vitals Are Abnormal",
        "Dear " + patient.getName() + ",<br><br>" +
        "Some of your recent vital signs are outside the normal range:<br><br>" +
        alertBody.toString() +
        "<br><br>Please consult your doctor immediately."
    );
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
// ----------MODULE 3--------------------
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
    System.out.println("\n==========================================");
    System.out.println("              EMAIL REMINDER");
    System.out.println("--------------------------------------------");
    System.out.println("To      : " + email);
    System.out.println("Reminder : " + message);
    System.out.println("Status  :    Sent Successfully");
    System.out.println("==========================================\n");
}

}

class SMSNotification implements Notifiable {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
public void sendNotification(String message) {
    System.out.println("\n===========================================");
    System.out.println("                SMS REMINDER");
    System.out.println("---------------------------------------------");
    System.out.println("To      : " + phoneNumber);
    System.out.println("Reminder : " + message);
    System.out.println("Status  :    Sent Successfully");
    System.out.println("===========================================\n");
}

}




class ReminderService {
    private static Scanner scanner = new Scanner(System.in);
private Set<Notifiable> channels = new HashSet<>();

    private ArrayList<String> notificationLog; // Track sent reminders

    public ReminderService() {
        channels = new HashSet<>();
        notificationLog = new ArrayList<>();
    }

  
    public void clearConsole() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}

    
    // For manual/general reminders like medication
    public void sendReminders(String message) {
        for (Notifiable channel : channels) {
            channel.sendNotification(message);
        }
    }

   public void checkAppointmentsAndSendReminders(Patient patient) {
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    for (Appointment appt : patient.getAppointment()) {
        // Only proceed if a reminder hasn't already been sent for this appointment
        if (appt.isReminderSent()) {
            continue;
        }
        
        clearConsole();
        
        long timeDiffMillis = appt.getAppointmentDate().getTime() - now.getTime();
        long minutesLeft = TimeUnit.MILLISECONDS.toMinutes(timeDiffMillis);

        // Check if the appointment is in the future and within 24 hours (1440 minutes)
        if (minutesLeft > 0 && minutesLeft <= 1440) {
            String message = "Reminder: Appointment with Dr. " + appt.getDoctor().getName() +
                             " at " + sdf.format(appt.getAppointmentDate());

            // Assuming you have valid channels already added,
            // you can use them directly to send the notification.
            try {
    // 1. Dummy email notification (for console print)
    Notifiable emailDummy = new EmailNotification(patient.getEmail());
    emailDummy.sendNotification(message);

    // 2. Real email sending (through Gmail)
    JavaEmailSender emailSender = new JavaEmailSender();
    emailSender.createAndSendEmail(
        patient.getEmail(), 
        "Appointment Reminder",
        "Dear " + patient.getName() + ",<br><br>" +
        "This is a reminder for your upcoming appointment:<br><br>" +
        "<b>Doctor:</b> " + appt.getDoctor().getName() + "<br>" +
        "<b>Date & Time:</b> " + sdf.format(appt.getAppointmentDate()) + "<br><br>" +
        "Please be on time.<br><br>Thank you!"
    );

    // 3. Dummy SMS notification (for console print)
    Notifiable sms = new SMSNotification(patient.getPhone());
    sms.sendNotification(message);

} catch (Exception e) {
    System.out.println("Error sending reminder: " + e.getMessage());
}

            
            // Mark this appointment as reminded so it won't trigger again.
            appt.setReminderSent(true);

            // Log the reminder
            notificationLog.add("[" + sdf.format(now) + "] " + message);
            
             // Pause to let the user see the reminder clearly
            try {
                Thread.sleep(5000);  // Wait for 1 second before proceeding
            } catch (InterruptedException e) {
                e.printStackTrace();
               
            }
             clearConsole();
        }
    }
}


    public ArrayList<String> getNotificationLog() {
        return notificationLog;
    }
}



// -----------------------------------------
// ----------MAIN METHOD--------------------
// -----------------------------------------
public class NewMain {
private static Scanner scanner = new Scanner(System.in);
private static Administrator admin = new Administrator(1, "Admin", "admin@hospital.com", "admin123", "Main Office", "Management", "033-00000000");
private static NotificationService notifier = new NotificationService();
private static EmergencyAlert emergencyAlert = new EmergencyAlert(notifier);
private static PanicButton panicButton = new PanicButton(notifier);
private static ReminderService reminderService = new ReminderService();
private static AppointmentManager appmanager=new AppointmentManager();
static ArrayList<Patient> patientList = new ArrayList<>();


    public static void main(String[] args) {
        //-------------------------------HARDCODED ELEMENTS---------------------------------------------
        // Hardcoded Patients
Patient p1 = new Patient(1, "Ali Khan", "10ameer5@gmail.com", "ali123", "House #12, F-10, Islamabad", "Male", "0312-1234567", "0300-9876543");
Patient p2 = new Patient(2, "Sara Ahmed", "10ameer5@gmail.com", "sara123", "Flat #5, Gulshan-e-Iqbal, Karachi", "Female", "0321-7654321", "0315-6789123");
Patient p3 = new Patient(3, "Usman Tariq", "10ameer5@gmail.com", "usman123", "Street #9, DHA Phase 3, Lahore", "Male", "0333-1122334", "0320-4455667");

// Hardcoded Doctors
Doctor d1 = new Doctor(101, "Dr. Ayesha Siddiqui", "zahraabbas394@gmail.com", "ayesha789", "Clinic #23, Blue Area, Islamabad", "Cardiologist", "0345-6789123");
Doctor d2 = new Doctor(102, "Dr. Bilal Hussain", "zahraabbas394@gmail.com", "bilal789", "Building #8, PECHS Block 6, Karachi", "Pulmonologist", "0301-2233445");
Doctor d3 = new Doctor(103, "Dr. Zainab Fatima", "zahraabbas394@gmail.com@gmail.com", "zainab789", "Plot #2, Model Town, Lahore", "Neurologist", "0316-5566778");

// Adding to admin's system
admin.addPatient(p1);
admin.addPatient(p2);
admin.addPatient(p3);

admin.addDoctor(d1);
admin.addDoctor(d2);
admin.addDoctor(d3);

// Assign patients to doctors
d1.addPatient(p1);
d2.addPatient(p2);
d3.addPatient(p3);

// Create dummy Dates (current date)
Date date1 = new Date();
Date date2 = new Date();
Date date3 = new Date();

// Create Appointments
Appointment app1 = new Appointment(date1, p1, d1);
Appointment app2 = new Appointment(date2, p2, d2);
Appointment app3 = new Appointment(date3, p3, d3);

appmanager.bookAppointment(app1);
appmanager.bookAppointment(app2);
appmanager.bookAppointment(app3);

p1.planAppointments(app1);
p2.planAppointments(app2);
p3.planAppointments(app3);

// --- Hardcoded Vital Signs ---
VitalSign vitals1 = new VitalSign(80, 18, "120/80", 97, 98.6, 22.5);
VitalSign vitals2 = new VitalSign(85, 20, "125/85", 96, 99.1, 24.0);
VitalSign vitals3 = new VitalSign(78, 17, "118/76", 98, 98.4, 21.8);

p1.addVitalSign(vitals1);
p2.addVitalSign(vitals2);
p3.addVitalSign(vitals3);

// --- Hardcoded Prescriptions ---
Prescription prescription1 = new Prescription("Paracetamol", "Twice a day after meals");
Prescription prescription2 = new Prescription("Ibuprofen", "Once a day at night");
Prescription prescription3 = new Prescription("Antibiotic (Amoxicillin)", "Three times a day");

Feedback feedback1 = new Feedback(d1, "Maintain hydration. Continue medication for 5 days.", prescription1);
Feedback feedback2 = new Feedback(d2, "Take rest. Regular exercise recommended.", prescription2);
Feedback feedback3 = new Feedback(d3, "Minor infection. Medication prescribed.", prescription3);

p1.addFeedbackToMedHistory(feedback1);
p2.addFeedbackToMedHistory(feedback2);
p3.addFeedbackToMedHistory(feedback3);


        while(true) {
            System.out.println("\n=======================================================");
            System.out.println("\n************REMOTE PATIENT MONITORING SYSTEM***********");
            System.out.println("\n=======================================================");
            System.out.println("\nLogin as:");
            System.out.println(" 1. Patient");
            System.out.println(" 2. Doctor");
            System.out.println(" 3. Administrator");
            System.out.println(" 4. Exit");
            System.out.println("---------------------------------------------------------");
            System.out.print("Choice: ");
            int role = Integer.parseInt(scanner.nextLine());

            if (role == 4) {
                System.out.println("-----------------Goodbye!----------------------------------");
                break;
            }

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            scanner.nextLine();

           switch (role) {
  case 1:
    Patient me = findPatient(admin.getPatients(), id, password);
    if (me != null) {
     patientMenu(me);
    } else {
      System.out.println("Invalid patient credentials.");
    }
    break;

  case 2:
    Doctor doc = findDoctor(admin.getDoctors(),id, password);
    if (doc != null) {
       doctorMenu(doc);
    } else {
      System.out.println("Invalid doctor credentials.");
    }
    break;

  case 3:
    Administrator adm = findAdmin(admin, id, password);
    if (adm != null) {
      adminMenu(admin);
    } else {
      System.out.println("Invalid admin credentials.");
    }
    break;
}
        }
        scanner.close();  
    }
    
    // ─── Patient Menu ───────────────────────────────────────────────────────────
private static void patientMenu(Patient patient)
{
    while (true) {
         // checks everytime the patient menu comes whether any patient has an appointmnet incoming or not 
             checkAllPatientsForUpcomingAppointments();
             
        System.out.println("-------------------------------------------------------------");
        System.out.println("                       PATIENT MENU         ");
        System.out.println("-------------------------------------------------------------");
        System.out.println(" 1. View Appointments");
        System.out.println(" 2. Book Appointments");
        System.out.println(" 3. Add Vitals");
        System.out.println(" 4. Panic Button");
        System.out.println(" 5. Video Call");
        System.out.println(" 6. Start Chat");
        System.out.println(" 7. Check Doctor Feedback");
        System.out.println(" 8. Logout");
        System.out.print("Choice: ");
        int patientopt = Integer.parseInt(scanner.nextLine());
        if (patientopt== 8) break;

        switch (patientopt) {
            case 1:
                viewPatientAppointments(patient);
                break;
            case 2:
                scheduleAppointment(patient);break;
            case 3:
                 recordVitalSigns(patient); break;
            case 4:
                triggerPanicButton(patient); break;
            case 5:
               generateVideoCall(patient); break;
            case 6:
                startChat(patient);break;
            case 7:
                 viewPatientHistory(patient);break; 
            default:
                System.out.println("Invalid choice.");
        }
    }
}

private static void doctorMenu(Doctor doctor) 
{
    while (true) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("                       DOCTOR MENU         ");
        System.out.println("-------------------------------------------------------------");
        System.out.println(" 1. Check Appointments");
        System.out.println(" 2. Check Vital Signs");
        System.out.println(" 3. Provide Feedback");
        System.out.println(" 4. View Medical History");
        System.out.println(" 5. View My Patient List");
        System.out.println(" 6. Logout");
        System.out.print("Choice: ");
        int d = Integer.parseInt(scanner.nextLine());
        if (d == 6) break;

        switch (d) {
            case 1:
                viewDoctorAppointments(doctor);
                break;
            case 2:
                
                checkEmergencyVitals(doctor);break;
            case 3:
                giveFeedback(doctor); break;
            case 4:
                viewDoctorPatientsHistory(doctor); break;
            case 5:
                viewDoctorPatients(doctor);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

// ─── Administrator Menu ─────────────────────────────────────────────────────
private static void adminMenu(  Administrator admin) {
    while (true) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("                    ADMINISTRATOR MENU         ");
        System.out.println("-------------------------------------------------------------");
        System.out.println(" 1. Add Patient");
        System.out.println(" 2. Add Doctor");
        System.out.println(" 3. View All Patients");
        System.out.println(" 4. View All Doctors");
        System.out.println(" 5. View all Appointments");
        System.out.println(" 6. Logout");
        System.out.print("Choice: ");
        int adminopt = Integer.parseInt(scanner.nextLine());
        if (adminopt == 6) break;

        switch (adminopt) {
            case 1:
                addPatient(); break;
            case 2:
                addDoctor(); break;
            case 3:
                viewAllPatients();break;
            case 4:
                viewAllDoctors();break;
            case 5:
                viewAllAppointments();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
private static Patient findPatient(List<Patient> patients, int id, String password) {
    for (Patient p : patients) {
        if (p.getId() == id
         && p.getPassword().equals(password)) {
            return p;
        }
    }
    return null;  // no match
}

// ─── Look up a Doctor by ID & password ─────────────────────────────────────
private static Doctor findDoctor(List<Doctor> doctors, int id, String password) {
    for (Doctor d : doctors) {
        if (d.getId() == id
         && d.getPassword().equals(password)) {
            return d;
        }
    }
    return null;  // no match
}

// ─── Verify Administrator credentials ──────────────────────────────────────
// (assuming you have a single admin instance)
private static Administrator findAdmin(Administrator admin, int id, String password) {
    if (admin.getId() == id
     && admin.getPassword().equals(password)) {
        return admin;
    }
    return null;
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
        patientList.add(patient);
           System.out.println("Patient has been added successfully.");
           
        System.out.println("----------------------------------------");
    }

   
    //---------func to display patient history---------
private static void viewPatientHistory(Patient patient) {

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
           System.out.println("Doctor has been added successfully.");
        System.out.println("----------------------------------------");
         
    }

    
    //---------func to give feedback---------
    private static void giveFeedback(Doctor doctor) {
       try {
        Patient patient = findPatient();
        if(patient == null) return;
        System.out.println("--------------------------------------");
        System.out.println("          GIVING FEEDBACK             ");
        System.out.println("--------------------------------------");
        
        //validate that the user enters non-empty strings for comments
        System.out.print("Enter comments: ");
        String comments = scanner.nextLine();
        if (comments.trim().isEmpty()) {
            throw new IllegalArgumentException("Comments can not be empty.");
        }
        
        //validate that the user enters non-empty strings for frequency
        System.out.print("Enter intake frequency:(once/twicw/thrice) ");
        String freq = scanner.nextLine();
        if (freq.trim().isEmpty()) {
            throw new IllegalArgumentException("Frequency cannot be empty.");
        }
        
        //validate that the user enters non-empty strings for medication
        System.out.print("Enter medication: ");
        String med = scanner.nextLine();
        if (med.trim().isEmpty()) {
            throw new IllegalArgumentException("Medication cannot be empty.");
        }
        
        Prescription pres = new Prescription(med, freq);
        doctor.provideFeedback(patient, comments, pres);
        System.out.println("Feedback has been given!");
          } catch (IllegalArgumentException e) {
        System.out.println("❌ Error: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while giving feedback: " + e.getMessage());
    }
     System.out.println("----------------------------------------");   
    }

    
    // ----------------func to start Start Doctor-Patient Chat---------------
private static void startChat(Patient patient) {
    Doctor chatDoctor = findDoctor();
    if (patient != null && chatDoctor != null) {
        ChatServer server = new ChatServer();
        ChatClient doctorClient = new ChatClient(chatDoctor.getName(), "Dr.", server);
        ChatClient patientClient = new ChatClient(patient.getName(), "Patient", server);
        doctorClient.startChatWith(patientClient);
    } else {
        System.out.println("Chat cannot be started because either patient or doctor was not found.");
    }
}

// ---------------func to Generate Video Call Link----------------
private static void generateVideoCall(Patient patient) {
    Doctor vcDoctor = findDoctor();
    if (patient != null && vcDoctor != null) {
        VideoCall.startCall(patient, vcDoctor);
    } else {
        System.out.println("Video call cannot be generated because either patient or doctor was not found.");
    }
}

//---------------func to check for Emergency Vitals-----------------
private static void checkEmergencyVitals(Doctor doctor) {
    Patient patientToCheck = findPatient();
    List<Patient> patient=doctor.getPatients();
    for(Patient pat : patient){
    if (patientToCheck == pat) {
        emergencyAlert.checkVitals(patientToCheck);
    } else {
        System.out.println("Patient not found for checking emergency vitals.");
    }
    }
    
    //-----------------------------------------------------
   

//-----------------------------------------------------------------------------
}

//--------------  Method for Trigger Panic Button--------------------
private static void triggerPanicButton(Patient patient) {
    
        panicButton.trigger(patient);
}

//  method to check whether a patient has an upcoming appointment or not
    private static void checkAllPatientsForUpcomingAppointments() {
    for (Patient patient : patientList) {
        
        // Check appointments
        reminderService.checkAppointmentsAndSendReminders(patient);
}
    }
    
    
    //---------- func to record vital signs of patient------------
    private static void recordVitalSigns(Patient patient) {
      
       
        System.out.println("--------------------------------------");
        System.out.println("            ADDING VITALS             ");
        System.out.println("--------------------------------------");
        try{
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
        
        //checks if user has entered negative statements
        if(pulse < 0 || resp < 0 || o2s < 0 || temp < 0 || bmi < 0) {
            throw new IllegalArgumentException("Vital sign values cannot be negative.");
        }
        
        VitalSign vs = new VitalSign(pulse, resp, bp, o2s, temp, bmi);
        patient.addVitalSign(vs);
        System.out.println("Vitals have been recorded!");
        
        //-----------catch statements-----------------
        
        //NumberFormatException for conversion issues
         } catch (NumberFormatException e) {
        System.out.println("Invalid number format. Please check the values you have entered.");
        
        //IllegalArgumentException for any logical checks you throw.
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        
        //generic Exception for any other issues.
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while recording vitals: " + e.getMessage());
    }
        System.out.println("----------------------------------------");
    }

 //---------func to find patient using id---------
private static Patient findPatient() {
    try {
        System.out.print("Enter patient ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        for (Patient p : admin.getPatients()) {  // <-- search in admin.getPatients()
            if (p.getId() == id) return p;
        }
        System.out.println("Patient not found!");
    } catch (Exception e) {
        System.out.println("Error reading patient ID. Please try again.");
    }
    return null;
}

//---------func to find doctor using id---------
private static Doctor findDoctor() {
    try {
        System.out.print("Enter doctor ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        for (Doctor d : admin.getDoctors()) {  // <-- search in admin.getDoctors()
            if (d.getId() == id) return d;
        }
        System.out.println("Doctor not found!");
    } catch (Exception e) {
        System.out.println("Error reading doctor ID. Please try again.");
    }
    return null;
}

    
    
private static void viewPatientAppointments(Patient patient) {
    List<Appointment> appts = patient.getAppointment();
    if (appts.isEmpty()) {
        System.out.println("You have no scheduled appointments.");
    } else {
        System.out.println("\nYour Appointments:");
        for (Appointment a : appts) {
            a.showAppointmentDetails();
        }
    }
}


//---------Doctor viewing Medical History of only their assigned patients---------
private static void viewDoctorPatientsHistory(Doctor doctor) {
    List<Patient> doctorPatients = doctor.getPatients();
    
    if (doctorPatients.isEmpty()) {
        System.out.println("You currently have no assigned patients.");
        return;
    }

    System.out.println("\nSelect a patient to view their Medical History:");
    for (int i = 0; i < doctorPatients.size(); i++) {
        System.out.println((i + 1) + ". " + doctorPatients.get(i).getName());
    }

    System.out.print("Enter your choice (number): ");
    try {
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice >= 1 && choice <= doctorPatients.size()) {
            Patient selectedPatient = doctorPatients.get(choice - 1);
            MedicalHistory history = selectedPatient.getMedicalHistory();
            
            System.out.println("\nMedical History for " + selectedPatient.getName() + ":");
            if (history.getFeedback().isEmpty()) {
                System.out.println("No medical history available for this patient.");
            } else {
                for (Feedback feedback : history.getFeedback()) {
                    System.out.println("--------------------------------------");
                    System.out.println("Doctor: " + feedback.getDoctor().getName());
                    System.out.println("Comments: " + feedback.getComments());
                    System.out.println("Prescription: " + feedback.getPrescription().getMedicine());
                    System.out.println("Frequency: " + feedback.getPrescription().getFrequency());
                    System.out.println("--------------------------------------");
                }
            }
        } else {
            System.out.println("Invalid choice. Please select a valid patient number.");
        }
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter a valid number.");
    }
}

//---------func to view doctor's appointments---------
private static void viewDoctorAppointments(Doctor doctor) {
    System.out.println("--------------------------------------");
    System.out.println("       Doctor's Appointments          ");
    System.out.println("--------------------------------------");

    boolean found = false;
    for (Appointment appt : appmanager.getAppointments()) {
        if (appt.getDoctor().getId() == doctor.getId()) {
            System.out.println("Appointment with Patient: " + appt.getPatient().getName());
            System.out.println("Appointment Date/Time: " + appt.getAppointmentDate());
            System.out.println("--------------------------------------");
            found = true;
        }
    }

    if (!found) {
        System.out.println("No appointments found for Dr. " + doctor.getName());
    }
}



// Print all appointments in the system (Admin view).
private static void viewAllAppointments() {
    ArrayList<Appointment> appointments = appmanager.getAppointments(); // from AppManager

    if (appointments.isEmpty()) {
        System.out.println("No appointments booked yet.");
    } else {
        System.out.println("\nAll Appointments:");
        for (Appointment a : appointments) {
            System.out.println("----------------------");
            System.out.println("Date: " + a.getAppointmentDate());
            System.out.println("Patient: " + a.getPatient().getName());
            System.out.println("Doctor: " + a.getDoctor().getName());
        }
    }
}

  //---------func to schedule an appointmnet---------
private static void scheduleAppointment(Patient patient) {
    System.out.println("--------------------------------------");
    System.out.println("          Booking Appointment         ");
    System.out.println("--------------------------------------");

    Doctor doctor = findDoctor();
   if(doctor == null) return;

    System.out.print("Enter date/time (dd-MM-yyyy HH:mm): ");
    String datetime = scanner.nextLine();

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date appointmentDate = sdf.parse(datetime);

        Appointment appt = new Appointment(appointmentDate, patient, doctor);
        patient.planAppointments(appt);
        appmanager.bookAppointment(appt);
        
        JavaEmailSender emailSender = new JavaEmailSender();

// Email to Patient
emailSender.createAndSendEmail(
    patient.getEmail(), 
    "Appointment Confirmation",
    "Dear " + patient.getName() + ",<br><br>Your appointment with Dr. " + doctor.getName() + " has been booked for " + appointmentDate + ".<br><br>Thank you!"
);

//                              Email to Doctor
//------------------------------------------------------------------------------------ 
emailSender.createAndSendEmail(
    doctor.getEmail(),
    "New Appointment Booked",
    "Dear Dr. " + doctor.getName() + ",<br><br>A new appointment has been booked with patient " + patient.getName() + " on " + appointmentDate + "."
);
//------------------------------------------------------------------------------------
        //Add patient to doctor's list
        if (!doctor.getPatients().contains(patient)) {
            doctor.addPatient(patient);  
        }

    } catch (java.text.ParseException e) {
        System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
    } catch (Exception e) {
        // You could log this or print a generic error message
        System.out.println("An unexpected error occurred while scheduling the appointment: " + e.getMessage());
    }
    System.out.println("----------------------------------------");
}
//----------------------viewAllPatients--------------------
private static void viewAllPatients() {
    ArrayList<Patient> patients = admin.getPatients(); // from administrator

    if (patients.isEmpty()) {
        System.out.println("No patients available.");
    } else {
        System.out.println("\nAll Patients:");
        for (Patient p : patients) {
            System.out.println("----------------------");
            System.out.println(p.toString());
        }
    }
}


//---------------------viewAllDoctors-----------------------
private static void viewAllDoctors() {
    ArrayList<Doctor> doctors = admin.getDoctors(); // from administrator

    if (doctors.isEmpty()) {
        System.out.println("No doctors available.");
    } else {
        System.out.println("\nAll Doctors:");
        for (Doctor d : doctors) {
            System.out.println("----------------------");
            System.out.println(d.toString());
        }
    }
}


//--------------------viewDoctorPatients---------------------------
private static void viewDoctorPatients(Doctor doctor) {
    ArrayList<Patient> patients = doctor.getPatients(); // from doctor object

    if (patients.isEmpty()) {
        System.out.println("You have no assigned patients yet.");
    } else {
        System.out.println("\nYour Patients:");
        for (Patient p : patients) {
            System.out.println("----------------------");
            System.out.println("Name: " + p.getName());
            System.out.println("Gender: " + p.getGender());
            System.out.println("Phone: " + p.getPhone());
        }
    }
}

    }



