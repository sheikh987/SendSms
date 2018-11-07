package com.example.sheikh.sendsms;

public class Message {

    private String mName;
    private String mOtp;
    private String mDateTime;

    public Message(String mName, String mOtp, String mDateTime) {
        this.mName = mName;
        this.mOtp = mOtp;
        this.mDateTime = mDateTime;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmOtp() {
        return mOtp;
    }

    public void setmOtp(String mOtp) {
        this.mOtp = mOtp;
    }

    public String getmDateTime() {
        return mDateTime;
    }

    public void setmDateTime(String mDateTime) {
        this.mDateTime = mDateTime;
    }




}
