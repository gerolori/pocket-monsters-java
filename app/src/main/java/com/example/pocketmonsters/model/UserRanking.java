package com.example.pocketmonsters.model;

public class UserRanking {
    private String uid;
    private String name;
    private String lifePoints;
    private String experiencePoints;
    private String profilePictureVersion;
    private Boolean positionShared;
    private String lat;
    private String lon;

    @Override
    public String toString() {
        return "UserRanking{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints='" + lifePoints + '\'' +
                ", experiencePoints='" + experiencePoints + '\'' +
                ", profilePictureVersion='" + profilePictureVersion + '\'' +
                ", positionShared=" + positionShared +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
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

    public String getProfilePictureVersion() {
        return profilePictureVersion;
    }

    public void setProfilePictureVersion(String profilePictureVersion) {
        this.profilePictureVersion = profilePictureVersion;
    }

    public Boolean getPositionShared() {
        return positionShared;
    }

    public void setPositionShared(Boolean positionShared) {
        this.positionShared = positionShared;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
