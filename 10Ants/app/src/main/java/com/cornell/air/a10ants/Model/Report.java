package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 6/06/2017.
 */

public class Report {
    //Variable instance
    private String id;
    private String propertyId;
    private String title;
    private String description;
    private String userName;
    private String dateSent;
    private String email;

    public Report(){
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
     * Return the value of the propertyId
     * @return return value
     */
    public String getPropertyId() {return propertyId;}
    /**
     * Set the value of the propertyId
     * @param propertyId variable to be loaded
     */
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}

    /**
     * Return the value of the title
     * @return return value
     */
    public String getTitle() {return title;}
    /**
     * Set the value of the title
     * @param title variable to be loaded
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Return the value of the description
     * @return return value
     */
    public String getDescription() {return description;}
    /**
     * Set the value of the description
     * @param description variable to be loaded
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Return the value of the userName
     * @return return value
     */
    public String getUserName() {return userName;}
    /**
     * Set the value of the userName
     * @param userName variable to be loaded
     */
    public void setUserName(String userName) {this.userName = userName;}

    /**
     * Return the value of the dataSent
     * @return return value
     */
    public String getDateSent() {return dateSent;}
    /**
     * Set the value of the dataSent
     * @param dataSent variable to be loaded
     */
    public void setDateSent(String dateSent) {this.dateSent = dateSent;}

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
}
