package com.example.pocketmonsters.model;

public class UserNearby {
    private String uid;
    private String name;
    private String lifePoints;
    private String experiencePoints;
    private String profileVersion;
    private String time;
    private String lat;
    private String lon;

    @Override
    public String toString() {
        return "UserNearby{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints='" + lifePoints + '\'' +
                ", experiencePoints='" + experiencePoints + '\'' +
                ", profileVersion='" + profileVersion + '\'' +
                ", time='" + time + '\'' +
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

    public String getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(String profileVersion) {
        this.profileVersion = profileVersion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
