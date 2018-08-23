package edu.palmirov.task_41.enteties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.LocalDate;

public class CurrencyRate {
    private int r030;
    private String currencyName;
    private double rate;
    private String abbreviation;
    private LocalDate date;

    public CurrencyRate(int r030, String currencyName, double rate, String abbreviation, LocalDate date) {
        this.r030 = r030;
        this.currencyName = currencyName;
        this.rate = rate;
        this.abbreviation = abbreviation;
        this.date = date;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CurrencyRate)) return false;

        CurrencyRate that = (CurrencyRate) o;

        return new EqualsBuilder()
                .append(r030, that.r030)
                .append(rate, that.rate)
                .append(currencyName, that.currencyName)
                .append(abbreviation, that.abbreviation)
                .append(date, that.date)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(r030)
                .append(currencyName)
                .append(rate)
                .append(abbreviation)
                .append(date)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("r030", r030)
                .append("currencyName", currencyName)
                .append("rate", rate)
                .append("abbreviation", abbreviation)
                .append("date", date)
                .toString();
    }
}