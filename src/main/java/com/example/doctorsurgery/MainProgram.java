package com.example.doctorsurgery;

import java.util.*;
import java.io.*;

public class MainProgram
{
    private static final DoctorList doctorList = new DoctorList();
    private static final PatientList patientList = new PatientList();
    private static final AppointmentList appointmentList = new AppointmentList();

    public static void main(String[] args)
    {
        File dataFile1 = new File("DoctorData.dat");
        File dataFile2 = new File("PatientData.dat");
        File dataFile3 = new File("AppointmentData.dat");

        if(!dataFile1.exists() || !dataFile2.exists() || !dataFile3.exists())
        {
            saveFile();
        }

        loadFile();

        boolean exit = false;
        while (!exit)
        {
            System.out.println();
            System.out.println("=====================================================");
            System.out.println("|   Welcome to Doctor's Surgery Management System   |");
            System.out.println("=====================================================");
            System.out.println();

            System.out.println();

            System.out.println("Select an Option: ");
            System.out.println("------------------");

            System.out.println("[1] Add a Doctor");
            System.out.println("[2] View All Doctors");
            System.out.println("[3] Update a Doctor Info");
            System.out.println("[4] Delete a Doctor");
            System.out.println("[5] Add a Patient");
            System.out.println("[6] View All Patients");
            System.out.println("[7] Update a Patient Info");
            System.out.println("[8] Delete a Patient");
            System.out.println("[9] Add an Appointment");
            System.out.println("[10] View All Appointments");
            System.out.println("[11] Update an Appointment Info");
            System.out.println("[12] Delete an Appointment");
            System.out.println("[13] Save & Exit");

            System.out.println();

            System.out.print("Enter Your Choice: ");
            int option = EasyScanner.nextInt();

            System.out.println();

            switch (option)
            {
                case 1 -> addDoctor();
                case 2 -> displayAllDoctors();
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> addPatient();
                case 6 -> displayAllPatients();
                case 7 -> updatePatient();
                case 8 -> deletePatient();
                case 9 -> addAppointment();
                case 10 -> displayAllAppointments();
                case 11 -> updateAppointment();
                case 12 -> deleteAppointment();
                case 13 ->
                {
                    saveFile();
                    exit = true;
                }
                default -> System.out.println("Invalid option!!! Please try again.");
            }
        }
        EasyScanner.close();
    }

    // METHOD FOR ADDING A NEW DOCTOR TO THE LIST
    public static void addDoctor()
    {
        System.out.print("Enter Doctor Name: ");
        String name = EasyScanner.nextLine();

        while (!name.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter Doctor Name: ");
            name = EasyScanner.nextLine();
        }

        System.out.print("Enter Doctor Speciality: ");
        String speciality = EasyScanner.nextLine();

        while (!speciality.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter Doctor Speciality: ");
            speciality = EasyScanner.nextLine();
        }

        Doctor newDoctor = new Doctor(0, name, speciality);
        doctorList.addDoctor(newDoctor);

        System.out.println();
        System.out.println("Alert: Doctor Added Successfully!!!");
        System.out.println();
    }

    // METHOD TO DELETE A DOCTOR BY THEIR ID FROM THE LIST
    public static void deleteDoctor()
    {
        System.out.print("Enter Doctor ID to Delete: ");
        int did = EasyScanner.nextInt();

        Doctor doctor = doctorList.getDoctorById(did);

        if (doctor != null)
        {
            doctorList.deleteDoctor(did);

            System.out.println();
            System.out.println("Alert: Doctor ID " + did + " Deleted Successfully!!!");
            System.out.println();
        }

        else
        {
            System.out.println();
            System.out.println("Error: Doctor ID Not Found!!!");
            System.out.println();
        }
    }

    // METHOD TO UPDATE A DOCTOR INFO RETRIEVED BY DOCTOR ID FROM THE LIST
    public static void updateDoctor()
    {
        System.out.print("Enter Doctor ID to Update: ");
        int idToUpdate = EasyScanner.nextInt();

        Doctor doctorToUpdate = doctorList.getDoctorById(idToUpdate);

        if (doctorToUpdate == null)
        {
            System.out.println("Alert: Doctor Not Found!!!");
        }

        else
        {
            System.out.print("Enter New Doctor Name: ");
            String newName = EasyScanner.nextLine();

            while (!newName.matches("[a-z A-Z]+"))
            {
                System.out.println();
                System.out.println("Error: a-z and/or A-Z Letter Only!!!");
                System.out.println();

                System.out.print("Enter New Doctor Name: ");
                newName = EasyScanner.nextLine();
            }

            System.out.print("Enter New Speciality: ");
            String newSpeciality = EasyScanner.nextLine();

            while (!newSpeciality.matches("[a-z A-Z]+"))
            {
                System.out.println();
                System.out.println("Error: a-z and/or A-Z Letter Only!!!");
                System.out.println();

                System.out.print("Enter New Speciality: ");
                newSpeciality = EasyScanner.nextLine();
            }

            if (!newName.isEmpty())
            {
                doctorToUpdate.setName(newName);
            }

            if (!newSpeciality.isEmpty())
            {
                doctorToUpdate.setSpeciality(newSpeciality);
            }

            System.out.println();
            System.out.println("Alert: Doctor Updated Successfully!!!");
            System.out.println();
        }
    }

    // METHOD TO DISPLAY ALL DOCTOR FROM THE LIST
    public static void displayAllDoctors()
    {
        System.out.println("List of All Doctor's: ");
        System.out.println("---------------------");

        for (Doctor doctor : doctorList.getDoctors())
        {
            System.out.println(doctor);
        }
    }

    // METHOD TO ADD A NEW PATIENT TO THE LIST
    public static void addPatient()
    {
        System.out.print("Enter Patient Name: ");
        String name = EasyScanner.nextLine();

        while (!name.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter Patient Name: ");
            name = EasyScanner.nextLine();
        }

        System.out.print("Enter Patient Age: ");
        int age = EasyScanner.nextInt();

        Patient newPatient = new Patient(0, name, age);
        patientList.addPatient(newPatient);

        System.out.println();
        System.out.println("Alert: Patient Added Successfully!!!");
        System.out.println();
    }

    // METHOD TO DELETE A PATIENT RETRIEVING BY THEIR ID FROM THE LIST

    public static void deletePatient()
    {
        System.out.print("Enter Patient ID to Delete: ");
        int pid = EasyScanner.nextInt();

        Patient patient = patientList.getPatientById(pid);
        if (patient != null)
        {
            patientList.deletePatient(pid);

            System.out.println();
            System.out.println("Alert: Patient ID " + pid + " Deleted Successfully!!!");
            System.out.println();
        }

        else
        {
            System.out.println();
            System.out.println("Error: Patient ID Not Found!!!");
            System.out.println();
        }
    }

    // METHOD TO UPDATE A PATIENT INFO CALLING BY THEIR PATIENT ID
    public static void updatePatient()
    {
        System.out.print("Enter Patient ID to Update: ");
        int idToUpdate = EasyScanner.nextInt();

        Patient patientToUpdate = patientList.getPatientById(idToUpdate);

        if (patientToUpdate == null)
        {
            System.out.println("Alert: Patient Not Found!!!");
        }

        else
        {
            System.out.print("Enter New Patient Name: ");
            String newName = EasyScanner.nextLine();

            while (!newName.matches("[a-z A-Z]+"))
            {
                System.out.println();
                System.out.println("Error: a-z and/or A-Z Letter Only!!!");
                System.out.println();

                System.out.print("Enter New Doctor Name: ");
                newName = EasyScanner.nextLine();
            }

            System.out.print("Enter New Age: ");
            int newAge = EasyScanner.nextInt();

            if (!newName.isEmpty())
            {
                patientToUpdate.setName(newName);
            }

            if (newAge != 0)
            {
                patientToUpdate.setAge(newAge);
            }

            System.out.println("Alert: Patient Updated Successfully!!!");
        }
    }

    // METHOD TO DISPLAY ALL PATIENT LIST FROM THE SAVED DATA
    public static void displayAllPatients()
    {
        System.out.println("List of All Patient's: ");
        System.out.println("---------------------");

        for (Patient patient : patientList.getPatients())
        {
            System.out.println(patient);
        }
    }

    // METHOD FOR CREATING A NEW APPOINTMENT ASSIGNING A DOCTOR WITH A PATIENT
    // ALSO ADDING REPORT OF DIAGNOSIS AND THEIR TREATMENT
    public static void addAppointment()
    {
        System.out.print("Enter Doctor ID: ");
        int did = EasyScanner.nextInt();

        Doctor doctor = doctorList.getDoctorById(did);
        if (doctor == null)
        {
            System.out.println("Error: Doctor ID " + did + " Doesn't Exist!!!");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int pid = EasyScanner.nextInt();

        Patient patient = patientList.getPatientById(pid);
        if (patient == null)
        {
            System.out.println("Error: Patient ID " + pid + " Doesn't Exist!!!");
            return;
        }

        System.out.print("Enter Diagnosis: ");
        String diagnosis = EasyScanner.nextLine();

        while (!diagnosis.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter Diagnosis: ");
            diagnosis = EasyScanner.nextLine();
        }

        System.out.print("Enter Department: ");
        String department = EasyScanner.nextLine();

        while (!department.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter department: ");
            department = EasyScanner.nextLine();
        }

        Appointment appointment = new Appointment(0, doctor, patient, diagnosis, department);
        appointmentList.addAppointment(appointment);

        System.out.println();
        System.out.println("Alert: Appointment ID " + appointment.getAppointmentId() + " Created Successfully!!!");
        System.out.println();
    }

    // METHOD TO DELETE AN APPOINTMENT FROM THE LIST SEARCHING BY IT'S APPOINTMENT ID
    public static void deleteAppointment()
    {
        System.out.print("Enter Appointment ID to Delete: ");
        int aid = EasyScanner.nextInt();

        Appointment appointment = appointmentList.getAppointmentById(aid);

        if (appointment != null)
        {
            appointmentList.deleteAppointment(aid);

            System.out.println();
            System.out.println("Alert: Appointment ID " + aid + " Deleted Successfully!!!");
            System.out.println();
        }

        else
        {
            System.out.println();
            System.out.println("Error: Appointment ID Not Found!!!");
            System.out.println();
        }
    }

    // METHOD TO UPDATE INFORMATION OF AN APPOINTMENT FROM THE LIST RETRIEVING BY APPOINTMENT ID
    public static void updateAppointment()
    {
        System.out.print("Enter Appointment ID: ");
        int id = EasyScanner.nextInt();

        Appointment appointmentToUpdate = appointmentList.getAppointmentById(id);
        if (appointmentToUpdate == null)
        {
            System.out.println();
            System.out.println("Error: Appointment ID Not Found!!!");
            System.out.println();
            return;
        }

        System.out.print("Enter New Doctor ID: ");
        int newDoctorId = EasyScanner.nextInt();

        System.out.print("Enter New Patient ID: ");
        int newPatientId = EasyScanner.nextInt();

        System.out.print("Enter New Diagnosis: ");
        String newDiagnosis = EasyScanner.nextLine();

        while (!newDiagnosis.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter New Diagnosis: ");
            newDiagnosis = EasyScanner.nextLine();
        }

        System.out.print("Enter New Department: ");
        String newDepartment = EasyScanner.nextLine();

        while (!newDepartment.matches("[a-z A-Z]+"))
        {
            System.out.println();
            System.out.println("Error: a-z and/or A-Z Letter Only!!!");
            System.out.println();

            System.out.print("Enter New Department: ");
            newDepartment = EasyScanner.nextLine();
        }

        Doctor newDoctor = doctorList.getDoctorById(newDoctorId);
        if (newDoctor == null)
        {
            System.out.println("Error: Doctor ID Not Found!!!");
            return;
        }

        Patient newPatient = patientList.getPatientById(newPatientId);
        if (newPatient == null)
        {
            System.out.println("Error: Patient ID Not Found!!!");
            return;
        }

        appointmentToUpdate.setDoctor(newDoctor);
        appointmentToUpdate.setPatient(newPatient);
        appointmentToUpdate.setDiagnosis(newDiagnosis);
        appointmentToUpdate.setDepartment(newDepartment);

        System.out.println();
        System.out.println("Alert: Appointment Updated Successfully!!!");
        System.out.println();
    }

    // METHOD TO DISPLAY ALL APPOINTMENT LIST FROM SAVED LIST
    public static void displayAllAppointments()
    {
        List<Appointment> appointments = appointmentList.getAppointment();
        if (appointments.isEmpty())
        {
            System.out.println();
            System.out.println("Error: No Appointment Found!!!");
            System.out.println();
        }

        else
        {
            System.out.println("Appointment List: ");
            System.out.println("-----------------");
            System.out.println();

            for (Appointment appointment : appointments)
            {
                System.out.println(appointment.toString());
                System.out.println();
            }
        }
    }

    // LOADING SAVED DATA FROM .DAT FILE TO THE RUNNING PROGRAM
    public static void loadFile()
    {
        try
        {
            // METHOD TO LOAD DOCTOR DATA

            FileReader doctorReader = new FileReader("DoctorData.dat");
            BufferedReader doctorBufferedReader = new BufferedReader(doctorReader);

            String doctorLine;

            while ((doctorLine = doctorBufferedReader.readLine()) != null)
            {
                String[] parts = doctorLine.split(",");

                int doctorId = Integer.parseInt(parts[0]);
                String doctorName = parts[1];
                String doctorSpecialty = parts[2];

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSpecialty);
                doctorList.addDoctor(doctor);
            }

            doctorBufferedReader.close();

            System.out.println();
            System.out.println("Alert: Doctor Data Loaded Successfully!!!");
            System.out.println();

            // METHOD TO LOAD PATIENT DATA

            FileReader patientReader = new FileReader("PatientData.dat");
            BufferedReader patientBufferedReader = new BufferedReader(patientReader);

            String patientLine;

            while ((patientLine = patientBufferedReader.readLine()) != null)
            {
                String[] parts = patientLine.split(",");

                int patientId = Integer.parseInt(parts[0]);
                String patientName = parts[1];
                int patientAge = Integer.parseInt(parts[0]);

                Patient patient = new Patient(patientId, patientName, patientAge);
                patientList.addPatient(patient);
            }

            patientBufferedReader.close();

            System.out.println("Alert: Patient Data Loaded Successfully!!!");
            System.out.println();

            // METHOD TO LOAD APPOINTMENT DATA

            FileReader appointmentReader = new FileReader("AppointmentData.dat");
            BufferedReader appointmentBufferedReader = new BufferedReader(appointmentReader);

            String appointmentLine;

            while ((appointmentLine = appointmentBufferedReader.readLine()) != null)
            {
                String[] parts = appointmentLine.split(",");

                int appointmentId = Integer.parseInt(parts[0]);
                int doctorId = Integer.parseInt(parts[0]);
                int patientId = Integer.parseInt(parts[0]);
                String diagnosis = parts[1];
                String department = parts[2];

                Doctor doctor = doctorList.getDoctorById(doctorId);
                Patient patient = patientList.getPatientById(patientId);

                Appointment appointment = new Appointment(appointmentId, doctor, patient, diagnosis, department);
                appointmentList.addAppointment(appointment);
            }

            appointmentBufferedReader.close();

            System.out.println("Alert: Appointment Data Loaded Successfully!!!");
            System.out.println();
        }

        catch (IOException e)
        {
            System.out.println();
            System.out.println("Error: Couldn't Load Data!!!");
            e.printStackTrace();
        }
    }

    // METHOD TO PERMANENTLY STORE DATA FROM RUNNING PROGRAM TO THE .DAT FILE
    public static void saveFile()
    {
        try
        {
            // METHOD TO SAVE DOCTOR DATA

            FileWriter doctorWriter = new FileWriter("DoctorData.dat");

            for (Doctor doctor : doctorList.getDoctors())
            {
                doctorWriter.write(doctor.getId() + ", " + doctor.getName() + ", " + doctor.getSpeciality() + " \n");
            }

            doctorWriter.close();

            // METHOD TO SAVE PATIENT DATA

            FileWriter patientWriter = new FileWriter("PatientData.dat");

            for (Patient patient : patientList.getPatients())
            {
                patientWriter.write(patient.getId() + ", " + patient.getName() + ", " + patient.getAge() + " \n");
            }

            patientWriter.close();

            // METHOD TO SAVE APPOINTMENT DATA

            FileWriter appointmentWriter = new FileWriter("AppointmentData.dat");

            for (Appointment appointment : appointmentList.getAppointment())
            {
                appointmentWriter.write(appointment.getAppointmentId() + ", " + appointment.getDoctor().getId() + ", " + appointment.getPatient().getId() + ", " + appointment.getDiagnosis() + ", " + appointment.getDepartment() + " \n");
            }

            appointmentWriter.close();

            System.out.println();
            System.out.println("Alert: Data Saved Successfully!!!");
            System.out.println();
        }

        // CATCH EXCEPTION METHOD
        catch (IOException e)
        {
            System.out.println();
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
}
