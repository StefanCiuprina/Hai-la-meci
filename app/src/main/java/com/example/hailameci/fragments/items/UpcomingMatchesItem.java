package com.example.hailameci.fragments.items;

public class UpcomingMatchesItem {

    private int imageTeamHome;
    private String nameTeamHome;
    private String nameTeamAway;
    private int imageTeamAway;
    private String date;
    private String stadium;

    public UpcomingMatchesItem(int imageTeamHome, String nameTeamHome, String nameTeamAway, int imageTeamAway, String date, String stadium) {
        this.imageTeamHome = imageTeamHome;
        this.nameTeamHome = nameTeamHome;
        this.nameTeamAway = nameTeamAway;
        this.imageTeamAway = imageTeamAway;
        this.date = date;
        this.stadium = stadium;
    }

    public int getImageTeamHome() {
        return imageTeamHome;
    }

    public String getNameTeamHome() {
        return nameTeamHome;
    }

    public String getNameTeamAway() {
        return nameTeamAway;
    }

    public int getImageTeamAway() {
        return imageTeamAway;
    }

    public String getDate() {
        return date;
    }

    public String getStadium() {
        return stadium;
    }
}
