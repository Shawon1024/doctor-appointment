package com.example.doctorsurgery;

import java.util.ArrayList;
import java.util.List;

public class DoctorList
{
    private final List<Doctor> doctors;

    public DoctorList()
    {
        doctors = new ArrayList<>();
    }

    // CREATE NEW DOCTOR WITH UNIQUE DOCTOR ID
    public void addDoctor(Doctor doctor)
    {
        int did = doctors.size() + 1;
        doctor.setId(did);
        doctors.add(doctor);
    }

    // READ DOCTOR BY ID
    public List<Doctor> getDoctors()
    {
        return doctors;
    }

    public Doctor getDoctorById(int did)
    {
        for (Doctor doctor : doctors)
        {
            if (doctor.getId() == did)
            {
                return doctor;
            }
        }
        return null;
    }

    // UPDATE A DOCTOR BY ID
    public void updateDoctor(int did, String newName, String newSpeciality)
    {
        Doctor doctorToUpdate = getDoctorById(did);

        if (doctorToUpdate != null)
        {
            doctorToUpdate.setName(newName);
            doctorToUpdate.setSpeciality(newSpeciality);
        }
    }

    // DELETE A DOCTOR BY ID
    public void deleteDoctor(int did)
    {
        Doctor doctorToDelete = getDoctorById(did);

        if (doctorToDelete != null)
        {
            doctors.remove(doctorToDelete);
        }
    }
}
