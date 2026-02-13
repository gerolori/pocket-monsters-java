package com.example.pocketmonsters.model;

public class VirtualItemDetail {

    private String id;
    private String name;
    private String image;
    private String level;
    private String lat;
    private String lon;
    private String type;

    @Override
    public String toString() {
        return "VirtualItemDetail{" +
                "itemID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", level='" + level + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
