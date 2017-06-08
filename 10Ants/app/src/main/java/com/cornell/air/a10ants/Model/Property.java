package com.cornell.air.a10ants.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;

/**
 * Created by root on 8/05/17.
 */

public class Property{
    //Variable instance
    private String id;
    private String name;
    private String address;
    private String description;
    private String type;
    private String email;

    public Property(){

    }

    public Property(String id, String name, String address, String description, String type){
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.type = type;
    }

    /**
     * Return the value of the id
     * @return return value
     */
    public String getId() {
        return id;
    }
    /**
     * Set the value of the id
     * @param id variable to be loaded
     */
    public void setId(String id) {this.id = id;}

    /**
     * Return the value of the name
     * @return return value
     */
    public String getName() {
        return name;
    }
    /**
     * Set the value of the name
     * @param name variable to be loaded
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the value of the address
     * @return return value
     */
    public String getAddress() {
        return address;
    }
    /**
     * Set the value of the address
     * @param address variable to be loaded
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return the value of the description
     * @return return value
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set the value of the description
     * @param description variable to be loaded
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the value of the type
     * @return return value
     */
    public String getType() { return type; }
    /**
     * Set the value of the type
     * @param type variable to be loaded
     */
    public void setType(String type) { this.type = type; }

    /**
     * Return the value of the email
     * @return return value
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set the value of the email
     * @param email variable to be loaded
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
