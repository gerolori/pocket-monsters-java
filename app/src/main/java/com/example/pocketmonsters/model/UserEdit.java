package com.example.pocketmonsters.model;

public class UserEdit {

    private String name;
    private String profilePicture;
    private boolean positionShared;

    @Override
    public String toString() {
        return "UserEdit{" +
                "name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", positionShared=" + positionShared +
                '}';
    }

    public UserEdit(String name, String profilePicture, boolean positionShared) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.positionShared = positionShared;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isPositionShared() {
        return positionShared;
    }

    public void setPositionShared(boolean positionShared) {
        this.positionShared = positionShared;
    }
}
