package com.example.doctorsurgery;

public class Patient
{
    private int pid;
    private String name;
    private int age;

    public Patient(int pid, String name, int age)
    {
        this.pid = pid;
        this.name = name;
        this.age = age;
    }

    public int getId()
    {
        return pid;
    }

    public void setId(int pid)
    {
        this.pid = pid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Patient ID: " + pid + "\nPatient Name: " + name + "\nPatient Age: " + age + "\n";
    }
}
