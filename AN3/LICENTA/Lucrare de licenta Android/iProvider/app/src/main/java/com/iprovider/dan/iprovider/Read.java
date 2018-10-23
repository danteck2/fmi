package com.iprovider.dan.iprovider;

public class Read {
    private String idMeter;
    private String firstLastName;
    private String uid;
    private String date;
    private String currentRead;

    public Read() {
    }

    public Read(String idMeter, String firstLastName, String uid, String date, String currentRead) {
        this.idMeter = idMeter;
        this.firstLastName = firstLastName;
        this.uid = uid;
        this.date = date;
        this.currentRead = currentRead;
    }
    public String getIdMeter() {
        return idMeter;
    }
    public void setIdMeter(String idMeter) {
        this.idMeter = idMeter;
    }
    public String getFirstLastName() {
        return firstLastName;
    }
    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCurrentRead() {
        return currentRead;
    }
    public void setCurrentRead(String currentRead) {
        this.currentRead = currentRead;
    }
}
