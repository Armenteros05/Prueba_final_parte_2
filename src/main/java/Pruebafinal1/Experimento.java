package Pruebafinal1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Experimento implements Serializable {
    private String name;
    private List<Bacteria> bacteriaPopulations;
    private String foodSupplyPattern; // Nuevo campo para el patrón de suministro de comida

    public Experimento(String name, String foodSupplyPattern) {
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

    public String getFoodSupplyPattern() {
        return foodSupplyPattern;
    }

    public String getName() {
        return name;
    }

}