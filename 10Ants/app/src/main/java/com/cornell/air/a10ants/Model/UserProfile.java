package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 5/06/2017.
 */

public class UserProfile {
    //Instance Variables
    private static String userName;
    private static String userEmail;
    private static String userProfile;
    private static String propertyId;
    private static String propertyName;

    /**
     * Return the value of the userName
     * @return return value
     */
    public static String getUserName() {return userName;}
    /**
     * Set the value of the userName
     * @param userName variable to be loaded
     */
    public static void setUserName(String userName) {UserProfile.userName = userName;}

    /**
     * Return the value of the userEmail
     * @return return value
     */
    public static String getUserEmail() {return userEmail;}
    /**
     * Set the value of the userEmail
     * @param userEmail variable to be loaded
     */
    public static void setUserEmail(String userEmail) {UserProfile.userEmail = userEmail;}

    /**
     * Return the value of the propertyId
     * @return return value
     */
    public static String getPropertyId() {return propertyId;}
    /**
     * Set the value of the userName
     * @param propertyId variable to be loaded
     */
    public static void setPropertyId(String propertyId) {UserProfile.propertyId = propertyId;}

    /**
     * Return the value of the userProfile
     * @return return value
     */
    public static String getUserProfile() {return userProfile;}
    /**
     * Set the value of the userName
     * @param userProfile variable to be loaded
     */
    public static void setUserProfile(String userProfile) {UserProfile.userProfile = userProfile;}

    /**
     * Return the value of the PropertyName
     * @return return value
     */
    public static String getPropertyName() {return propertyName;}
    /**
     * Set the value of the propertyName
     * @param propertyName variable to be loaded
     */
    public static void setPropertyName(String propertyName) {UserProfile.propertyName = propertyName;}
}
