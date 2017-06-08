package com.cornell.air.a10ants.Model;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by massami on 29/05/2017.
 */
public class Expense {
    //Instantiate
    private String id;
    private String amount;
    private String expense;
    private String paidTo;
    private String paidOn;
    private String propertyId;

    public Expense(){

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
     * Return the value of the amount
     * @return return value
     */
    public String getAmount() {return amount;}
    /**
     * Set the value of the amount
     * @param amount variable to be loaded
     */
    public void setAmount(String amount) {this.amount = amount;}

    /**
     * Return the value of the expense
     * @return return value
     */
    public String getExpense() {return expense;}
    /**
     * Set the value of the expense
     * @param expense variable to be loaded
     */
    public void setExpense(String expense) {this.expense = expense;}

    /**
     * Return the value of PaidTo
     * @return return value
     */
    public String getPaidTo() {return paidTo;}
    /**
     * Set the value of the PaidTo
     * @param paidTo variable to be loaded
     */
    public void setPaidTo(String paidTo) {this.paidTo = paidTo;}

    /**
     * Return the value of PaidOn
     * @return return value
     */
    public String getPaidOn() {return paidOn;}
    /**
     * Set the value of the PaidOn
     * @param paidOn variable to be loaded
     */
    public void setPaidOn(String paidOn) {this.paidOn = paidOn;}

    /**
     * Return the value of propertyId
     * @return return value
     */
    public String getPropertyId() {return propertyId;}
    /**
     * Set the value of the propertyId
     * @param propertyId variable to be loaded
     */
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}
}
