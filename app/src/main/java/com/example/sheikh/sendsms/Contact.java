package com.example.sheikh.sendsms;

public class Contact {

    private String mFirstName;
    private String mLastName;
    private String mPhoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {

        mFirstName = firstName;
        mLastName = lastName;
        mPhoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPhoneNumber() { return mPhoneNumber; }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {this.mPhoneNumber = phoneNumber;}
}
