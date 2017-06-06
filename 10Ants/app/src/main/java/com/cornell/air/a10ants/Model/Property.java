package com.cornell.air.a10ants.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;

/**
 * Created by root on 8/05/17.
 */

public class Property{

    private String id;
    private String name;
    private String address;
    private String description;
    private String type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
