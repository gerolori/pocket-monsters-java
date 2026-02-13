package com.example.pocketmonsters.data.local.user;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private int uid;

    @NonNull
    private int profilePictureVersion;

    private String profilePicture;
    private String name;
    private int lifePoints;
    private int experiencePoints;
    private String equippedWeapon;
    private String equippedArmor;
    private String equippedAmulet;
    private boolean positionShared;

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", profilePictureVersion=" + profilePictureVersion +
                ", profilePicture='" + profilePicture + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", experiencePoints=" + experiencePoints +
                ", equippedWeapon='" + equippedWeapon + '\'' +
                ", equippedArmor='" + equippedArmor + '\'' +
                ", equippedAmulet='" + equippedAmulet + '\'' +
                ", positionShared=" + positionShared +
                '}';
    }

    @NonNull
    public int getUid() {
        return uid;
    }

    public void setUid(@NonNull int uid) {
        this.uid = uid;
    }

    public int getProfilePictureVersion() {
        return profilePictureVersion;
    }

    public void setProfilePictureVersion(int profilePictureVersion) {
        this.profilePictureVersion = profilePictureVersion;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
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

    public boolean isPositionShared() {
        return positionShared;
    }

    public void setPositionShared(boolean positionShared) {
        this.positionShared = positionShared;
    }
}
