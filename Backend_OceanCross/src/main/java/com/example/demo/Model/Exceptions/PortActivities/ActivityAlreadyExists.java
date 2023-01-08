package com.example.demo.Model.Exceptions.PortActivities;

public class ActivityAlreadyExists extends RuntimeException{
    public ActivityAlreadyExists(){super("Activity already exists in user schedule");}
}
