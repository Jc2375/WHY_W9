package com.example.why_w9;

public class Customer extends User{
    String email;
    double password;
    public Customer(String email, double paswword) {
        this.email=email;
        this.password=password;
    }
    public String typeOfUser()
    {
        return "Customer";
    }
}
