package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 6/06/2017.
 */

public class Report {
    private String id;
    private String propertyId;
    private String title;
    private String description;
    private String userName;
    private String dateSent;
    private String email;

    public Report(){
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getPropertyId() {return propertyId;}
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getDateSent() {return dateSent;}
    public void setDateSent(String dateSent) {this.dateSent = dateSent;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
