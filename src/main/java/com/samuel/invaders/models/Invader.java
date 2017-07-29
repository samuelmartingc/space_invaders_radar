package com.samuel.invaders.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 29/07/17.
 */
public class Invader {
    private String name;
    private int height;
    private int width;
    private List<String> body;
    public Invader(String name, List<String> body){
        this.name = name;
        this.body = body;
        if (body.equals(new ArrayList<String>())) {
            this.height = 0;
            this.width = 0;
        } else {
            this.height = body.size();
            this.width = body.get(0).length();
        }
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<String> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Invader: " + name +
                "name='" + name + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invader invader = (Invader) o;

        if (getHeight() != invader.getHeight()) return false;
        if (getWidth() != invader.getWidth()) return false;
        if (getName() != null ? !getName().equals(invader.getName()) : invader.getName() != null) return false;
        return getBody() != null ? getBody().equals(invader.getBody()) : invader.getBody() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getHeight();
        result = 31 * result + getWidth();
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        return result;
    }
}
