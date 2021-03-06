package com.example.easyjobs.Objects;

import java.io.Serializable;

public class User implements Serializable
{
    private String User_ID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isPremium;
    private String email;
    private float rating;
    private int ratingsAmount;

    public User() {}

    public User(String User_ID, String firstName, String lastName, String phoneNumber, boolean isPremium, float rating, int ratingsAmount, String email)
    {
        this.User_ID = User_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isPremium = isPremium;
        this.rating = rating;
        this.ratingsAmount = ratingsAmount;
        this.email = email;
    }

    public String getUser_ID()
    {
        return User_ID;
    }

    public void setUser_ID(String user_ID)
    {
        User_ID = user_ID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPremium()
    {
        return isPremium;
    }

    public void setPremium(boolean premium)
    {
        isPremium = premium;
    }

    public float getRating()
    {
        return rating;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }

    public int getRatingsAmount()
    {
        return ratingsAmount;
    }

    public void setRatingsAmount(int ratingsAmount)
    {
        this.ratingsAmount = ratingsAmount;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "User{" + "User_ID='" + User_ID + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", isPremium=" + isPremium + ", email='" + email + '\'' + ", rating=" + rating + ", ratingsAmount=" + ratingsAmount + '}';
    }
}
