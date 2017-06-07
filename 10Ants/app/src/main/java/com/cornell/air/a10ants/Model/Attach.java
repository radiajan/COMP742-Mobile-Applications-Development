package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 6/06/2017.
 */

public class Attach {
    private String id;
    private String propertyId;
    private String name;

    public Attach(){
    }

    public Attach(String id, String propertyId, String name){
        this.id = id;
        this.propertyId = propertyId;
        this.name = name;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getPropertyId() {return propertyId;}
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
