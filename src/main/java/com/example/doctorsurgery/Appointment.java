package com.example.doctorsurgery;

public class Appointment
{
    private int appointmentId;
    private Doctor doctor;
    private Patient patient;
    private String diagnosis;
    private String department;

    public Appointment(int appointmentId, Doctor doctor, Patient patient, String diagnosis, String department)
    {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.department = department;
    }

    public int getAppointmentId()
    {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }

    public Doctor getDoctor()
    {
        return doctor;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public String getDiagnosis()
    {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis)
    {
        this.diagnosis = diagnosis;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return "Appointment ID: " + appointmentId + "\n\n" + doctor + "\n" + patient + "Diagnosis: " + diagnosis + "\nTreatment: " + department + "\n";
    }
}
