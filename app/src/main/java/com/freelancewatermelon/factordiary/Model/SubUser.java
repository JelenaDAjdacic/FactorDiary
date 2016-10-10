package com.freelancewatermelon.factordiary.Model;

public class SubUser {
    //name and address string
    private String firstName;
    private String lastName;
    private boolean active;

    public SubUser() {
      /*Blank default constructor essential for Firebase*/
    }

    //Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
