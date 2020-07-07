package com.example.hailameci.fragments.items;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.hailameci.R;

public class PlansItem extends ContextWrapper {

    private int imageTeamHome;
    private String homeTeam;
    private String awayTeam;
    private int imageTeamAway;
    private String date;
    private String stadium;
    private boolean isHomeMatch;
    private double ticketPrice;
    private int numberOfTickets;
    private String arrivalDate;
    private String leavingDate;
    private String placeToStay;

    public PlansItem(Context base, int imageTeamHome, String homeTeam, String awayTeam, int imageTeamAway, String date, String stadium, boolean isHomeMatch,
                     double ticketPrice, int numberOfTickets, String arrivalDate, String leavingDate, String placeToStay) {
        super(base);
        this.imageTeamHome = imageTeamHome;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.imageTeamAway = imageTeamAway;
        this.date = date;
        this.stadium = stadium;
        this.isHomeMatch = isHomeMatch;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.placeToStay = placeToStay;
    }

    public String getDate() {
        return date;
    }

    public String getStadium() {
        return stadium;
    }

    public int getImageTeamHome() {
        return imageTeamHome;
    }

    public int getImageTeamAway() {
        return imageTeamAway;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String isHomeMatch() {
        return isHomeMatch ? getString(R.string.home_match) : getString(R.string.away_match);
    }

    public String getTicketPrice() {
        return getString(R.string.price_of_ticket) + ": " + String.valueOf(ticketPrice);
    }

    public String getNumberOfTickets() {
        return getString(R.string.number_of_tickets) + ": " + String.valueOf(numberOfTickets);
    }

    public String getTotalPrice() {
        return getString(R.string.price_total) + ": " + String.valueOf(ticketPrice * numberOfTickets);
    }

    public String getArrivalDate() {
        return getString(R.string.arrival_date) + ": " + arrivalDate;
    }

    public String getLeavingDate() {
        return getString(R.string.leave_date) + ": " + leavingDate;
    }

    public String getPlaceToStay() {
        return getString(R.string.place_to_stay) + ": " + placeToStay;
    }
}
