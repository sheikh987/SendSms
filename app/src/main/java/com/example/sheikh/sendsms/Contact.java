package com.example.sheikh.sendsms;

public class Contact {

    private String mFirstName;
    private String mLastName;

    public Contact(String firstName, String lastName) {

        mFirstName = firstName;
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }
}
