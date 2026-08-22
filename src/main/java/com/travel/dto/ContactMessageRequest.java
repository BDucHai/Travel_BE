package com.travel.dto;

public class ContactMessageRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String subject;
    private String message;
    private String nationality;
    private String contactMethod;
    private String hearFrom;

    public ContactMessageRequest() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getNationality() {
        return nationality;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public String getHearFrom() {
        return hearFrom;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public void setHearFrom(String hearFrom) {
        this.hearFrom = hearFrom;
    }
}