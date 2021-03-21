package com.example.why_w9;

public class User
{
    String email;
    double password;
    public User()
    {
        this.email="Customer@gmail.com";
        this.password=1234;
    }
    public User(String email, double password)
    {
        this.email=email;
        this.password=password;
    }
    public String typeOfUser()
    {
        return "Default User";
    }
    //return email
    public String getEmail()
    {
        return email;
    }
    public double getPassword()
    {
        return password;
    }
}
