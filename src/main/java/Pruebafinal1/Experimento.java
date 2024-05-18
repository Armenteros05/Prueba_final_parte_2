package Pruebafinal1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Experimento implements Serializable {
    private String name;
    private List<Bacteria> bacteriaPopulations;
    private String foodSupplyPattern; // Nuevo campo para el patrón de suministro de comida

    public Experimento(String name) {
        this.name = name;
        this.foodSupplyPattern = foodSupplyPattern;
        this.bacteriaPopulations = new ArrayList<>();
    }

    public Experimento() {
        this.bacteriaPopulations = new ArrayList<>();
    }

    public List<Bacteria> getBacteriaPopulations() {
        return bacteriaPopulations;
    }

    public void setBacteriaPopulations(List<Bacteria> bacteriaPopulations) {
        this.bacteriaPopulations = bacteriaPopulations;
    }

    public String getFoodSupplyPattern() {
        return foodSupplyPattern;
    }

    public void setFoodSupplyPattern(String foodSupplyPattern) {
        this.foodSupplyPattern = foodSupplyPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBacteriaPopulation(Bacteria bacteria) {
        this.bacteriaPopulations.add(bacteria);
    }

    public void removeBacteriaPopulation(Bacteria bacteria) {
        this.bacteriaPopulations.remove(bacteria);
    }
}