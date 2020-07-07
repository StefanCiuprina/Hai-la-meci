package com.example.hailameci.fragments.items;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.hailameci.R;

public class LeagueTableItem extends ContextWrapper implements Comparable<LeagueTableItem> {

    private String position;
    private int positionInt;
    private int image;
    private String teamName;
    private String m, v, e, i, g, p;

    public LeagueTableItem(Context base, int position, String teamNameShort, int m, int v, int e, int i, int g, int p) {
        super(base);
        this.position = (position < 10) ? " " : "";
        this.position += String.valueOf(position);
        this.position += ".";
        this.positionInt = position;
        this.image = getImage(teamNameShort);
        this.teamName = getTeamName(teamNameShort);
        this.m = String.valueOf(m);
        this.v = String.valueOf(v);
        this.e = String.valueOf(e);
        this.i = String.valueOf(i);
        this.g = (g >= 0) ? " " : "";
        this.g += String.valueOf(g);
        this.p = String.valueOf(p);
    }

    public LeagueTableItem(Context base) {
        super(base);
        this.m = getString(R.string.games_played);
        this.v = getString(R.string.victories);
        this.e = getString(R.string.draws);
        this.i = getString(R.string.losses);
        this.g = " " + getString(R.string.goal_difference);
        this.p = " " + getString(R.string.points);
    }

    private int getImage(String teamNameShort) {
        switch(teamNameShort) {
            case "astra":
                return R.drawable.cl_astra;
            case "botosani":
                return R.drawable.cl_botosani;
            case "cfr":
                return R.drawable.cl_cfr;
            case "chindia":
                return R.drawable.cl_chindia;
            case "clinceni":
                return R.drawable.cl_clinceni;
            case "craiova":
                return R.drawable.cl_craiova;
            case "dinamo":
                return R.drawable.cl_dinamo;
            case "fcsb":
                return R.drawable.cl_fcsb;
            case "gazmetan":
                return R.drawable.cl_gazmetan;
            case "hermannstadt":
                return R.drawable.cl_hermannstadt;
            case "iasi":
                return R.drawable.cl_iasi;
            case "sepsi":
                return R.drawable.cl_sepsi;
            case "viitorul":
                return R.drawable.cl_viitorul;
            case "voluntari":
                return R.drawable.cl_voluntari;
        }
        return 0;
    }

    private String getTeamName(String teamNameShort) {
        switch(teamNameShort) {
            case "astra":
                return getString(R.string.astra);
            case "botosani":
                return getString(R.string.botosani);
            case "cfr":
                return getString(R.string.cfr);
            case "chindia":
                return getString(R.string.chindia);
            case "clinceni":
                return getString(R.string.clinceni);
            case "craiova":
                return getString(R.string.craiova);
            case "dinamo":
                return getString(R.string.dinamo);
            case "fcsb":
                return getString(R.string.fcsb);
            case "gazmetan":
                return getString(R.string.gazmetan);
            case "hermannstadt":
                return getString(R.string.hermannstadt);
            case "iasi":
                return getString(R.string.iasi);
            case "sepsi":
                return getString(R.string.sepsi);
            case "viitorul":
                return getString(R.string.viitorul);
            case "voluntari":
                return getString(R.string.voluntari);
        }
        return null;
    }

    @Override
    public int compareTo(LeagueTableItem o) {
        return (this.positionInt > o.getPositionInt()) ? 1 : -1;
    }

    public int getPositionInt() {
        return positionInt;
    }

    public String getPosition() {
        return position;
    }

    public int getImage() {
        return image;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getM() {
        return m;
    }

    public String getV() {
        return v;
    }

    public String getE() {
        return e;
    }

    public String getI() {
        return i;
    }

    public String getG() {
        return g;
    }

    public String getP() {
        return p;
    }
}
