package com.rsd_movieshop.model;

public class Admin extends User{
    boolean isAdmin = true;

    public Admin(int userID, String familyName, String firstName, String email, String passwordHash, String userName) {
        super(userID, familyName, firstName, email, passwordHash, userName);
    }
}
