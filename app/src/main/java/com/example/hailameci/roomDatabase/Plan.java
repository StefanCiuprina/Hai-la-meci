package com.example.hailameci.roomDatabase;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hailameci.R;
import com.example.hailameci.fragments.items.PlansItem;

@Entity(tableName = "plan_table")
public class Plan {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String homeTeam;
    private String awayTeam;
    private String date;
    private String stadium;
    private boolean isHomeMatch;
    private double ticketPrice;
    private int numberOfTickets;
    private String arrivalDate;
    private String leavingDate;
    private String placeToStay;

    public Plan(String homeTeam, String awayTeam, String date, String stadium,
                boolean isHomeMatch, double ticketPrice, int numberOfTickets,
                String arrivalDate, String leavingDate, String placeToStay) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.stadium = stadium;
        this.isHomeMatch = isHomeMatch;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;
        this.arrivalDate = arrivalDate;
        this.leavingDate = leavingDate;
        this.placeToStay = placeToStay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStadium() {
        return stadium;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public boolean isHomeMatch() {
        return isHomeMatch;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public String getPlaceToStay() {
        return placeToStay;
    }

    public PlansItem convertToPlansItem(Context context) {
        int imageHomeTeam = getImage(homeTeam);
        int imageAwayTeam = getImage(awayTeam);
        return new PlansItem(context, imageHomeTeam, homeTeam, awayTeam, imageAwayTeam, date, stadium,
                isHomeMatch, ticketPrice, numberOfTickets, arrivalDate, leavingDate, placeToStay);
    }

    private int getImage(String teamName) {
        switch (teamName) {
            case "Astra Giurgiu":
                return R.drawable.cl_astra;
            case "FC Botoșani":
                return R.drawable.cl_botosani;
            case "CFR Cluj":
                return R.drawable.cl_cfr;
            case "Chindia Târgoviște":
                return R.drawable.cl_chindia;
            case "Academica Clinceni":
                return R.drawable.cl_clinceni;
            case "CS U Craiova":
                return R.drawable.cl_craiova;
            case "Dinamo":
                return R.drawable.cl_dinamo;
            case "FCSB":
                return R.drawable.cl_fcsb;
            case "Gaz Metan Mediaș":
                return R.drawable.cl_gazmetan;
            case "Hermannstadt":
                return R.drawable.cl_hermannstadt;
            case "CSM Poli Iași":
                return R.drawable.cl_iasi;
            case "Sepsi OSK":
                return R.drawable.cl_sepsi;
            case "FC Viitorul":
                return R.drawable.cl_viitorul;
            case "FC Voluntari":
                return R.drawable.cl_voluntari;
        }
        return 0;
    }
}
