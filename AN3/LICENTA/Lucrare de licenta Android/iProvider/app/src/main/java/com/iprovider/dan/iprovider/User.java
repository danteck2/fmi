package com.iprovider.dan.iprovider;

public class User {

    private String uid;
    private String firstLastName;
    private String email;
    private String phone;
    private String address;
    private String birthday;
    private String typeUser;
    private boolean verified;

    public User(String uid, String firstLastName, String email, String phone, String address, String birthday, String typeUser, boolean verified) {
        this.uid = uid;
        this.firstLastName = firstLastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.typeUser = typeUser;
        this.verified = verified;
    }
    public User() {
        this.uid = "";
        this.firstLastName = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.birthday = "";
        this.verified = false;
    }

    public String getUid() {
        if(uid != null)
            return uid;
        else
            return "";
    }
    public String getFirstLastName() {
        return firstLastName;
    }
    public String getEmail() {
        if(email != null)
            return email;
        else
            return "";
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getBirthday() {
        return birthday;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public String getTypeUser() {
        return typeUser;
    }
    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
