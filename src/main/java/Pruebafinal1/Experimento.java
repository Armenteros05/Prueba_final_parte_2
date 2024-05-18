package Pruebafinal1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Experimento implements Serializable {
    private String name;
    private List<Bacteria> bacteriaPopulations;

    public Experimento(String name) {
        this.name = name;
        this.bacteriaPopulations = new ArrayList<>();
    }

    public Experimento() {
        this.bacteriaPopulations = new ArrayList<>();
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

    public List<Bacteria> getBacteriaPopulations() {
        return this.bacteriaPopulations;
    }

    @Override
    public String toString() {
        return "Experimento{" +
                "name='" + name + '\'' +
                ", bacteriaPopulations=" + bacteriaPopulations +
                '}';
    }
}