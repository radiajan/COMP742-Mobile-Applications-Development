package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 29/05/2017.
 */

public class Tenant {
    private String id;
    private String name;
    private String email;
    private int phone;
    private String dateOfBirth;
    private String propertyId;

    public Tenant(){

    }

    public Tenant(String id, String name, String email, int phone, String dateOfBirth, String propertyId){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.propertyId = propertyId;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getPhone() {return phone;}
    public void setPhone(int phone) {this.phone = phone;}

    public String getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public String getPropertyId() {return propertyId;}
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}
}
