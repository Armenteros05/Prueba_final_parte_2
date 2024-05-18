package Pruebafinal1;

import Pruebafinal1.Bacteria;
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

    public void addBacteriaPopulation(Bacteria bacteria) {
        this.bacteriaPopulations.add(bacteria);
    }

    public List<Bacteria> getBacteriaPopulations() {
        return this.bacteriaPopulations;
    }
}