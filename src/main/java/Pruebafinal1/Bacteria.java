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
    private long foodDoseMicrograms; // Cambiado a long para microgramos
    private int duration; // Duración variable del experimento

    public Bacteria(String name, Date startDate, Date endDate, int initialBacteriaCount, double temperature, String lightCondition, long foodDoseMicrograms) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.foodDoseMicrograms = foodDoseMicrograms;
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getInitialBacteriaCount() {
        return this.initialBacteriaCount;
    }

    public void setInitialBacteriaCount(int initialBacteriaCount) {
        this.initialBacteriaCount = initialBacteriaCount;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getLightCondition() {
        return this.lightCondition;
    }

    public void setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
    }

    public long getFoodDose() {
        return this.foodDoseMicrograms;
    }

    public void setFoodDose(long foodDoseMicrograms) {
        this.foodDoseMicrograms = foodDoseMicrograms;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}