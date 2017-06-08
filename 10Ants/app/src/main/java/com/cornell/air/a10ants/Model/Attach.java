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
     * Return the value of the name
     * @return return value
     */
    public String getName() {return name;}
    /**
     * Set the value of the name
     * @param name variable to be loaded
     */
    public void setName(String name) {this.name = name;}
}
