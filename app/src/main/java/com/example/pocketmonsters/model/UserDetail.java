package com.example.pocketmonsters.model;

public class UserDetail {

    private String uid;
    private String name;
    private String lifePoints;
    private String experiencePoints;
    private String equippedWeapon;
    private String equippedArmor;
    private String equippedAmulet;
    private String profilePicture;
    private Boolean positionShared;

    @Override
    public String toString() {
        return "UserDetail{" +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints='" + lifePoints + '\'' +
                ", experiencePoints='" + experiencePoints + '\'' +
                ", equippedWeapon='" + equippedWeapon + '\'' +
                ", equippedArmor='" + equippedArmor + '\'' +
                ", equippedAmulet='" + equippedAmulet + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", sharedPosition=" + positionShared +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(String lifePoints) {
        this.lifePoints = lifePoints;
    }

    public String getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(String experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(String equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public String getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(String equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public String getEquippedAmulet() {
        return equippedAmulet;
    }

    public void setEquippedAmulet(String equippedAmulet) {
        this.equippedAmulet = equippedAmulet;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getPositionShared() {
        return positionShared;
    }

    public void setPositionShared(Boolean positionShared) {
        this.positionShared = positionShared;
    }
}
