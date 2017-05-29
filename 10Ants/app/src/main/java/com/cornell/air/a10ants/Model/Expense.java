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
    private String payTo;
    private String paidOn;

    public Expense(){

    }

    public Expense(String id ,String amount, String expense, String payTo, String paidOn){
        this.id = id;
        this.amount = amount;
        this.expense = expense;
        this.payTo = payTo;
        this.paidOn = paidOn;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getAmount() {return amount;}
    public void setAmount(String amount) {this.amount = amount;}

    public String getExpense() {return expense;}
    public void setExpense(String expense) {this.expense = expense;}

    public String getPayTo() {return payTo;}
    public void setPayTo(String payTo) {this.payTo = payTo;}

    public String getPaidOn() {return paidOn;}
    public void setPaidOn(String paidOn) {this.paidOn = paidOn;}


}
