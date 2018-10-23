package com.iprovider.dan.iprovider;

public class Customer {

    private String id;
    private String idMeter;
    private String name;
    private String accountNumber;
    private String email;
    private String phone;
    private String address;
    private String uidUser;
    private String lastReadDate;
    private String lastRead;
    private String balance;
    private String nationalIdNumber;

    public Customer() {
    }

    public Customer(String id, String idMeter, String name, String accountNumber, String email, String phone, String address, String uidUser, String lastReadDate, String lastRead, String balance, String nationalIdNumber) {
        this.id = id;
        this.idMeter = idMeter;
        this.name = name;
        this.accountNumber = accountNumber;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.uidUser = uidUser;
        this.lastReadDate = lastReadDate;
        this.lastRead = lastRead;
        this.balance = balance;
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getIdMeter() {
        return idMeter;
    }
    public void setIdMeter(String idMeter) {
        this.idMeter = idMeter;
    }
    public String getUidUser() {
        return uidUser;
    }
    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }
    public String getLastRead() {
        return lastRead;
    }
    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }
    public String getLastReadDate() {
        return lastReadDate;
    }
    public void setLastReadDate(String lastReadDate) {
        this.lastReadDate = lastReadDate;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }
    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }
}
