package com.example.demo.Model.Exceptions.PortActivities;

public class PortActivityScheduleNotFound extends RuntimeException
{
    public PortActivityScheduleNotFound(Long id){super("Error at adding port activity to user"+id.toString());}
}
