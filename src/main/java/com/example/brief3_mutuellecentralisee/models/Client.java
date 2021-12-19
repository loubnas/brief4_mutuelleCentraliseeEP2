package com.example.brief3_mutuellecentralisee.models;

public class Client {
    private  String nameCompany;
    private String dateStartWork;
    private String firstName;
    private String lastName;
    private String cinpass;
    private String phone;
    private String email;
    private String adress;
    private String dateRegister;
    private String numBadgework;

    // Constructeur :
    public Client(String nameCompany, String dateStartWork, String firstName, String lastName, String cinpass, String phone, String email, String adress ,String dateRegister,String numBadgework) {
        this.nameCompany = nameCompany;
        this.dateStartWork = dateStartWork;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cinpass = cinpass;
        this.phone = phone;
        this.email = email;
        this.adress = adress;
        this.dateRegister=dateRegister;
        this.numBadgework=numBadgework;
    }
    public  Client(){

    }

    // Getters & Setters :

    public String getNameCompany() {return nameCompany;}
    public void setNameCompany(String nameCompany) {this.nameCompany = nameCompany;}
    public String getDateStartWork() {return dateStartWork;}
    public void setDateStartWork(String dateStartWork) {this.dateStartWork = dateStartWork;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getCinpass() {return cinpass;}
    public void setCinpass(String cinpass) {this.cinpass = cinpass;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getAdress() {return adress;}
    public void setAdress(String adress) {this.adress = adress;}
    public String getDateRegister() {return dateRegister;}
    public void setDateRegister(String dateRegister) {this.dateRegister = dateRegister;}
    public String getNumBadgework() {return numBadgework;}
    public void setNumBadgework(String numBadgework) {this.numBadgework = numBadgework;}
}
