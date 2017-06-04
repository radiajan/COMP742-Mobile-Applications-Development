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

    public Expense(String id ,String amount, String expense, String paidTo, String paidOn, String propertyId){
        this.id = id;
        this.amount = amount;
        this.expense = expense;
        this.paidTo = paidTo;
        this.paidOn = paidOn;
        this.propertyId = propertyId;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getAmount() {return amount;}
    public void setAmount(String amount) {this.amount = amount;}

    public String getExpense() {return expense;}
    public void setExpense(String expense) {this.expense = expense;}

    public String getPaidTo() {return paidTo;}
    public void setPaidTo(String paidTo) {this.paidTo = paidTo;}

    public String getPaidOn() {return paidOn;}
    public void setPaidOn(String paidOn) {this.paidOn = paidOn;}

    public String getPropertyId() {return propertyId;}
    public void setPropertyId(String propertyId) {this.propertyId = propertyId;}
}
