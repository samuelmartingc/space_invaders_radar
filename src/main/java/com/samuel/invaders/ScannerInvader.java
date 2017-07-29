package com.samuel.invaders;

import com.samuel.invaders.builders.InvaderBuilder;
import com.samuel.invaders.models.Invader;
import com.samuel.invaders.models.MatchInvader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScannerInvader {
    private static final String PARTIAL = "partial_";
    private final InvaderBuilder invaderBuilder;

    public ScannerInvader(InvaderBuilder invaderBuilder) {
        this.invaderBuilder = invaderBuilder;
    }

    public List<MatchInvader>  scan(double certainty, List<Invader> invaders, List<String> map) {
        List<List<MatchInvader>> matches = new ArrayList<>();
        for (Invader invader:invaders){
            List<MatchInvader> matchInvaders = scanInvader(certainty, invader, map);
            matches.add(matchInvaders);
        }
        return flatMatch(matches);
    }

    private List<MatchInvader> scanInvader(double certainty, Invader invader, List<String> map) {
        List<List<MatchInvader>> matches = new ArrayList<>();
        matches.add(upperEdgeScan(certainty, invader, map));
        matches.add(scanRows(certainty, invader, map));
        matches.add(lowerEdgeScan(certainty, invader, map));
        return flatMatch(matches);
    }

    private List<MatchInvader> upperEdgeScan(double certainty, Invader invader, List<String> map) {
        List<List<MatchInvader>> matches = new ArrayList<>();
        int limit = invader.getHeight()-1; // one line is not enough for catching invaders
        for (int i=0;i<limit;i++){
            List<String> subMap = map.subList(0, invader.getHeight()-i);
            Invader subInvader = invaderBuilder.buildInvaderFromSubMap(PARTIAL +invader.getName(),
                    invader.getBody(),invader.getHeight()-i,invader.getWidth(),i,0);
            List<MatchInvader> matchInvaders = scanRows(certainty, subInvader, subMap);
            matches.add(matchInvaders);
        }
        return flatMatch(matches);

    }

    private List<MatchInvader> lowerEdgeScan(double certainty, Invader invader, List<String> map) {
        List<List<MatchInvader>> matches = new ArrayList<>();
        int limit = invader.getHeight()-1; // one line is not enough for catching invaders
        for (int i=0;i<limit;i++){
            List<String> subMap = map.subList(map.size()-invader.getHeight() + i, map.size());
            Invader subInvader = invaderBuilder.buildInvaderFromSubMap(PARTIAL +invader.getName(),
                    invader.getBody(),invader.getHeight()-i,invader.getWidth(),0,0);
            List<MatchInvader> matchInvaders = scanRows(certainty, subInvader, subMap);
            matches.add(matchInvaders);
        }
        return flatMatch(matches);
    }


    private List<MatchInvader> scanRows(double certainty, Invader invader, List<String> map){
        int rowEnd = map.size() - (invader.getHeight() - 1);
        List<List<MatchInvader>> matches = new ArrayList<>();
        for (int i = 0;i<rowEnd;i++){
            List<String> subMap = map.subList(i, i + invader.getHeight());
            List<MatchInvader> matchInvaders = scanColumns(certainty, invader, subMap);
            matches.add(matchInvaders);
        }
        return flatMatch(matches);
    }

    private List<MatchInvader> scanColumns(double certainty, Invader invader, List<String> map){
        int length = map.isEmpty()?0:(map.get(0).length() - invader.getWidth() + 1);
        List<MatchInvader> matchInvaders = new ArrayList<>();
        for(int i = 0; i< length; i++){
            MatchInvader matchInvader = scanPosition(0,i,invader,map);
            if (matchInvader.getCertainty() >= certainty){
                matchInvaders.add(matchInvader);
            }
        }
        return matchInvaders;
    }

    private MatchInvader scanPosition(int verticalStart, int horizontalStart, Invader invader, List<String> map){
        Invader generated = invaderBuilder.buildInvaderFromSubMap(invader.getName(),
                map,invader.getHeight(),invader.getWidth(),verticalStart,horizontalStart);
        double certainty = compare(invader,generated);
        return new MatchInvader(invader.getName(),certainty, generated);
    }

    private double compare(Invader invader, Invader unknown) {
        double match = 0;
        double error = 0;
        int currentLine = 0;
        for (String line : unknown.getBody()) {
            for (int i = 0; i<line.length();i++){
                boolean isMatch = invader.getBody().get(currentLine).charAt(i) == line.charAt(i);
                if (isMatch){
                    match++;
                } else {
                    error++;
                }
            }
            currentLine++;
        }
        return match / (match + error);
    }

    private List<MatchInvader> flatMatch(List<List<MatchInvader>> matches) {
        return matches.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
