package edu.palmirov.task_40.enteties;

import edu.palmirov.task_40.interfaces.Databasable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Automobile implements Databasable {
    private String marque;
    private String model;
    private Transmission transmissionType;
    private int yearOfProduction;
    private int price;
    private int produced;
    private String producingCountry;

    public enum Transmission {
        AT("Automatic Transmission"),
        MT("Manual Transmission"),
        AM("Automated Manual Transmission"),
        CVT("Continuously Variable Transmission");

        private final String info;

        Transmission(final String info){
            this.info = info;
        }

        @Override
        public String toString() {
            return info;
        }
    }

    public Automobile() {}

    public Automobile(String marque, String model, Transmission transmissionType, int yearOfProduction, int price, int produced, String producingCountry) {
        this.marque = marque;
        this.model = model;
        this.transmissionType = transmissionType;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
        this.produced = produced;
        this.producingCountry = producingCountry;
    }

    public Automobile (String sqlString){
        this.fromDatabase(sqlString);
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Transmission getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(Transmission transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }

    @Override
    public String createTableQuery(){
        return "CREATE TABLE IF NOT EXISTS automobiles" +
                "(id INT PRIMARY KEY auto_increment," +
                " marque VARCHAR(50) NOT NULL," +
                " model VARCHAR(50)," +
                " transmission_type VARCHAR(50)," +
                " year_of_production YEAR(4)," +
                " price DECIMAL(15, 2)," +
                " produced INT," +
                " producing_country VARCHAR(50))";
    }

    @Override
    public String toDatabase(){
        return "INSERT INTO automobiles " +
                "(marque, model, transmission_type, year_of_production, price, produced, producing_country)" +
                " VALUES " +
                "( '" + this.marque + "', '" + this.model + "', '" + this.transmissionType.toString()  + "', " +
                this.yearOfProduction + ", " + this.price + ", " + this.produced + ", '" +
                this.producingCountry + "' );";
    }

    @Override
    public void fromDatabase(String sqlString){
        List<String> strings = new LinkedList<>(Arrays.asList(sqlString.split(",")));
        strings.remove(0);
        for(int i = 0; i < strings.size(); i++){
            switch (i){
                case 0:
                    this.setMarque(strings.get(0).trim());
                    break;
                case 1:
                    this.setModel(strings.get(1).trim());
                    break;
                case 2:
                    switch (strings.get(2).trim()){
                        case "Automatic Transmission":
                            this.setTransmissionType(Automobile.Transmission.AT);
                            break;
                        case "Manual Transmission":
                            this.setTransmissionType(Automobile.Transmission.MT);
                            break;
                        case "Automated Manual Transmission":
                            this.setTransmissionType(Automobile.Transmission.AM);
                            break;
                        case "Continuously Variable Transmission":
                            this.setTransmissionType(Automobile.Transmission.CVT);
                            break;
                    }
                case 3:
                    this.setYearOfProduction(
                            Integer.parseInt(strings.get(3).trim().substring(0, 4)));
                    break;
                case 4:
                    this.setPrice(
                            (int)Double.parseDouble(strings.get(4).trim()));
                    break;
                case 5:
                    this.setProduced(
                            Integer.parseInt(strings.get(5).trim()));
                    break;
                case 6:
                    this.setProducingCountry(strings.get(6).trim());
                    break;
            }
        }
    }

    public String sqlRaw(){
        return "marque = '" + this.marque + "' AND " +
                "model = '" + this.model + "' AND " +
                "transmission_type = '" + this.transmissionType +"' AND " +
                "year_of_production = " + this.yearOfProduction + " AND " +
                "price = " + this.price + " AND " +
                "produced = " + this.produced + " AND " +
                "producing_country = '" + this.producingCountry + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Automobile that = (Automobile) o;

        return new EqualsBuilder()
                .append(yearOfProduction, that.yearOfProduction)
                .append(price, that.price)
                .append(produced, that.produced)
                .append(marque, that.marque)
                .append(model, that.model)
                .append(transmissionType, that.transmissionType)
                .append(producingCountry, that.producingCountry)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(marque)
                .append(model)
                .append(transmissionType)
                .append(yearOfProduction)
                .append(price)
                .append(produced)
                .append(producingCountry)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("marque", marque)
                .append("model", model)
                .append("transmissionType", transmissionType)
                .append("yearOfProduction", yearOfProduction)
                .append("price", price)
                .append("produced", produced)
                .append("producingCountry", producingCountry)
                .toString();
    }
}