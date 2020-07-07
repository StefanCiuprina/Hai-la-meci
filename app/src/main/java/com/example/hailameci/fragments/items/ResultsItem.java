package com.example.hailameci.fragments.items;

public class ResultsItem {

    private int imageTeamHome;
    private String nameTeamHome;
    private String nameTeamAway;
    private int imageTeamAway;
    private String date;
    private String stadium;

    private String scoreHome;
    private String scoreAway;

    public ResultsItem(int imageTeamHome, String nameTeamHome, String nameTeamAway, int imageTeamAway, String date, String stadium, int scoreHome, int scoreAway) {
        this.imageTeamHome = imageTeamHome;
        this.nameTeamHome = nameTeamHome;
        this.nameTeamAway = nameTeamAway;
        this.imageTeamAway = imageTeamAway;
        this.date = date;
        this.stadium = stadium;

        this.scoreHome = String.valueOf(scoreHome);
        this.scoreAway = String.valueOf(scoreAway);
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

    public String getScoreHome() {
        return scoreHome;
    }

    public String getScoreAway() {
        return scoreAway;
    }
}
