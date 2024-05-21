package com.example.doctorsurgery;

import java.util.ArrayList;
import java.util.List;

public class PatientList
{
    private final List<Patient> patients;

    public PatientList()
    {
        patients = new ArrayList<>();
    }

    // CREATE NEW PATIENT WITH UNIQUE PATIENT ID
    public void addPatient(Patient patient)
    {
        int pid = patients.size() + 1;
        patient.setId(pid);
        patients.add(patient);
    }

    // READ PATIENT BY ID
    public List<Patient> getPatients()
    {
        return patients;
    }

    public Patient getPatientById(int pid)
    {
        for (Patient patient : patients)
        {
            if (patient.getId() == pid)
            {
                return patient;
            }
        }
        return null;
    }

    // UPDATE PATIENT BY ID

    public void updatePatient(int pid, String newName, int newAge)
    {
        Patient patientToUpdate = getPatientById(pid);

        if (patientToUpdate != null)
        {
            patientToUpdate.setName(newName);
            patientToUpdate.setAge(newAge);
        }
    }

    // DELETE A PATIENT BY ID
    public void deletePatient(int pid)
    {
        Patient patientToDelete = getPatientById(pid);

        if (patientToDelete != null)
        {
            patients.remove(patientToDelete);
        }
    }
}
