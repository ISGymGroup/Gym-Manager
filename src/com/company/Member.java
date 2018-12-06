package com.company;
import java.util.*;

public class Member {

    private String NAME;
    private int MEMBER_ID;
    private boolean ATTENDING = false;

    public void set_Name(String mName)
    {
        NAME = mName;
    }

    public void set_ID(int mID)
    {
        MEMBER_ID = mID;
    }

    public void checkIn()
    {
        ATTENDING = true;
    }
    public void check_Out()
    {
        ATTENDING = false;
    }
    public boolean getAttendence()
    {
        return ATTENDING;
    }

    public String get_Name()
    {
        return NAME;
    }

    public int get_ID()
    {
        return MEMBER_ID;
    }



    public void display_Info()
    {
        System.out.printf("Name : %s%n Member ID : %d%n ", NAME, MEMBER_ID);
    }

}
