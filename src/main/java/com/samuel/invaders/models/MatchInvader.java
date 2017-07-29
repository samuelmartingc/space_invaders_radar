package com.samuel.invaders.models;

/**
 * Created by samuel on 29/07/17.
 */
public class MatchInvader {
    private String name;
    private double certainty;
    private Invader invader;

    public MatchInvader(String name, double certainty, Invader invader) {
        this.name = name;
        this.certainty = certainty;
        this.invader = invader;
    }

    public String getName() {
        return name;
    }

    public double getCertainty() {
        return certainty;
    }

    public Invader getInvader() {
        return invader;
    }

    @Override
    public String toString() {
        String invaderPrinted = String.join("\n", invader.getBody());
        return "MatchInvader{" +
                "name='" + name + '\'' +
                ", certainty=" + certainty +
                ", invader=" + invader +
                "}\n" + invaderPrinted + "\n\n";
    }
}
