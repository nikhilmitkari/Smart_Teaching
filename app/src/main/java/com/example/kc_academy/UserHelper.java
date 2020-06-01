package com.example.kc_academy;

public class UserHelper {

    String name, phone, dob;

    public UserHelper() {
    }

    public UserHelper(String name, String phone, String dob) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
