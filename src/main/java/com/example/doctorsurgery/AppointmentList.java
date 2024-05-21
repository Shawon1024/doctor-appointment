package com.example.doctorsurgery;

import java.util.ArrayList;
import java.util.List;

public class AppointmentList
{
    private final List<Appointment> appointments;

    public AppointmentList()
    {
        appointments = new ArrayList<>();
    }

    // CREATE NEW APPOINTMENT WITH UNIQUE APPOINTMENT ID
    public void addAppointment(Appointment appointment)
    {
        int aid = appointments.size() + 1;
        appointment.setAppointmentId(aid);
        appointments.add(appointment);
    }

    // VIEW APPOINTMENT BY ID
    public List<Appointment> getAppointment()
    {
        return appointments;
    }

    public Appointment getAppointmentById(int aid)
    {
        for (Appointment appointment : appointments)
        {
            if (appointment.getAppointmentId() == aid)
            {
                return appointment;
            }
        }
        return null;
    }

    // UPDATE APPOINTMENT BY ID
    public void updateAppointment(int aid, Doctor newDoctorId, Patient newPatientId, String newDiagnosis, String newDepartment)
    {
        Appointment appointmentToUpdate = getAppointmentById(aid);

        if (appointmentToUpdate != null)
        {
            appointmentToUpdate.setDoctor(newDoctorId);
            appointmentToUpdate.setPatient(newPatientId);
            appointmentToUpdate.setDiagnosis(newDiagnosis);
            appointmentToUpdate.setDepartment(newDepartment);
        }
    }

    // DELETE APPOINTMENT BY ID
    public void deleteAppointment(int aid)
    {
        Appointment appointmentToDelete = getAppointmentById(aid);

        if (appointmentToDelete != null)
        {
            appointments.remove(appointmentToDelete);
        }
    }
}
