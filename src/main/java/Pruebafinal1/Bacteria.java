package Pruebafinal1;

import java.io.Serializable;
import java.util.Date;

public class Bacteria implements Serializable {
    private String name;
    private Date startDate;
    private Date endDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private int foodDose;

    public Bacteria(String name, Date startDate, Date endDate, int initialBacteriaCount, double temperature, String lightCondition, int foodDose) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.foodDose = foodDose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getInitialBacteriaCount() {
        return initialBacteriaCount;
    }

    public void setInitialBacteriaCount(int initialBacteriaCount) {
        this.initialBacteriaCount = initialBacteriaCount;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getLightCondition() {
        return lightCondition;
    }

    public void setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
    }

    public int getFoodDose() {
        return foodDose;
    }

    public void setFoodDose(int foodDose) {
        this.foodDose = foodDose;
    }
}