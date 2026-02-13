package com.example.pocketmonsters.model;

public class VirtualItemActivated {

    private String lifeStatus;
    private String lifePoints;
    private String experiencePoints;
    private String weapon;
    private String armor;
    private String amulet;

    @Override
    public String toString() {
        return "VirtualItemActivated{" +
                "lifeStatus='" + lifeStatus + '\'' +
                ", lifePoints='" + lifePoints + '\'' +
                ", experiencePoints='" + experiencePoints + '\'' +
                ", weapon='" + weapon + '\'' +
                ", armor='" + armor + '\'' +
                ", amulet='" + amulet + '\'' +
                '}';
    }

    public String getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(String lifeStatus) {
        this.lifeStatus = lifeStatus;
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

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getAmulet() {
        return amulet;
    }

    public void setAmulet(String amulet) {
        this.amulet = amulet;
    }
}
