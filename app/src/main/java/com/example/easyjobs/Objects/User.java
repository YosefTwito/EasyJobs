package com.example.easyjobs.Objects;

public class User {
    String User_ID;
    String firstName;
    String lastName;
    String phoneNumber;
    boolean isPremium;
    // PROF PIC // THINK HOW?
    float rating;
    int ratingsAmount;

    public User()
    {

    }

    public User(String User_ID, String firstName, String lastName, String phoneNumber, boolean isPremium, float rating, int ratingsAmount) {
        this.User_ID = User_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isPremium = isPremium;
        this.rating = rating;
        this.ratingsAmount = ratingsAmount;
    }



    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public float getRating() {
        return rating;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingsAmount() {
        return ratingsAmount;
    }

    public void setRatingsAmount(int ratingsAmount) {
        this.ratingsAmount = ratingsAmount;
    }


    @Override
    public String toString() {
        return "User{" +
                "User_ID='" + User_ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isPremium=" + isPremium +
                ", rating=" + rating +
                ", ratingsAmount=" + ratingsAmount +
                '}';
    }
}
