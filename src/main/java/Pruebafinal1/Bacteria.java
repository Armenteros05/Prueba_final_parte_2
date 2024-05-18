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

    public Bacteria(String name, Date startDate, Date endDate, int initialBacteriaCount, double temperature, String lightCondition, long foodDoseMicrograms, int duration) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.foodDoseMicrograms = foodDoseMicrograms;
        this.duration = duration;
    }

    // Getters y setters restantes...
}