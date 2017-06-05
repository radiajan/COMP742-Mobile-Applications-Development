package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 5/06/2017.
 */

public class UserProfile {
    //Instance Variables
    private static String userName;
    private static String userEmail;
    private static String propertyId;
    private static String userProfile;

    //Getters and setters
    public static String getUserName() {return userName;}
    public static void setUserName(String userName) {UserProfile.userName = userName;}

    public static String getUserEmail() {return userEmail;}
    public static void setUserEmail(String userEmail) {UserProfile.userEmail = userEmail;}

    public static String getPropertyId() {return propertyId;}
    public static void setPropertyId(String propertyId) {UserProfile.propertyId = propertyId;}

    public static String getUserProfile() {return userProfile;}
    public static void setUserProfile(String userProfile) {UserProfile.userProfile = userProfile;}


}
