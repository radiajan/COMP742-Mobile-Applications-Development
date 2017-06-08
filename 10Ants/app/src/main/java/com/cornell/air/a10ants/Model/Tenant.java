package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 29/05/2017.
 */

public class Tenant {
    //Variable instance
    private String id;
    private String name;
    private String email;
    private int phone;
    private String dateOfBirth;
    private String propertyId;

    public Tenant(){

    }

    /**
     * Return the value of the id
     * @return return value
     */
    public String getId() {return id;}
    /**
     * Set the value of the id
     * @param id variable to be loaded
     */
    public void setId(String id) {this.id = id;}

    /**
     * Return the value of the name
     * @return return value
     */
    public String getName() {return name;}
    /**
     * Set the value of the name
     * @param name variable to be loaded
     */
    public void setName(String name) {this.name = name;}

    /**
     * Return the value of the email
     * @return return value
     */
    public String getEmail() {return email;}
    /**
     * Set the value of the email
     * @param email variable to be loaded
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * Return the value of the phone
     * @return return value
     */
    public int getPhone() {return phone;}
    /**
     * Set the value of the phone
     * @param phone variable to be loaded
     */
    public void setPhone(int phone) {this.phone = phone;}

    /**
     * Return the value of the dateOfBirth
     * @return return value
     */
    public String getDateOfBirth() {return dateOfBirth;}
    /**
     * Set the value of the dateOfBirth
     * @param dateOfBirth variable to be loaded
     */
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    /**
     * Return the value of the propertyId
     * @return return value
     */
    public String getPropertyId() {return propertyId;}
    /**
     * Set the value of the propertyId
     * @param propertyId variable to be loaded
     */
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}
}
