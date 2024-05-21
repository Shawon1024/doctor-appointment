package com.example.doctorsurgery;

public class Doctor
{
    private int did;
    private String name;
    private String speciality;

    public Doctor(int did, String name, String speciality)
    {
        this.did = did;
        this.name = name;
        this.speciality = speciality;
    }

    public int getId()
    {
        return did;
    }

    public void setId(int did)
    {
        this.did = did;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public void setSpeciality(String speciality)
    {
        this.speciality = speciality;
    }

    @Override
    public String toString()
    {
        return "Doctor ID: " + did + "\nDoctor Name: " + name + "\nDoctor Speciality: " + speciality + "\n";
    }
}
