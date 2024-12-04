package com.usebouncer.model;

import java.util.Objects;

public class Credits {

    Integer credits;

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credits credits1 = (Credits) o;
        return Objects.equals(credits, credits1.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credits);
    }

    @Override
    public String toString() {
        return "Credits{" +
                "credits=" + credits +
                '}';
    }
}
