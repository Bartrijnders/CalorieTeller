package main.java.org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandaardMaaltijd implements Maaltijd {

    private final List<Toevoeging> toevoegingen;
    private String naam;
    private  double calorieDoel;
    private double koolhydraatDoel;
    private double eiwitDoel;
    private double vetDoel;
    private UUID id;

    public StandaardMaaltijd(List<Toevoeging> toevoegingen, String naam,  double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel, UUID id) {
        this.naam = naam;
        this.toevoegingen = toevoegingen;
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.vetDoel = vetDoel;
        this.id = id;
    }


    public StandaardMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        this.naam = naam;
        this.toevoegingen = new ArrayList<>();
        this.id = UUID.randomUUID();
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.vetDoel = vetDoel;
    }

    public StandaardMaaltijd(UUID id, String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        this.naam = naam;
        this.toevoegingen = new ArrayList<>();
        this.id = id;
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.vetDoel = vetDoel;
    }

    @Override
    public UUID getId(){return id;}

    @Override
    public List<Toevoeging> getToevoegingen() {
        return toevoegingen;
    }

    @Override
    public double getCalorieDoel() {
        return calorieDoel;
    }

    @Override
    public void setCalorieDoel(double calorieDoel) {
        this.calorieDoel = calorieDoel;
    }

    @Override
    public double getKoolhydraatDoel() {
        return koolhydraatDoel;
    }

    @Override
    public void setKoolhydraatDoel(double koolhydraatDoel) {
        this.koolhydraatDoel = koolhydraatDoel;
    }

    @Override
    public double getEiwitDoel() {
        return eiwitDoel;
    }

    @Override
    public void setEiwitDoel(double eiwitDoel) {
        this.eiwitDoel = eiwitDoel;
    }

    @Override
    public double getVetDoel() {
        return vetDoel;
    }

    @Override
    public void setVetDoel(double vetDoel) {
        this.vetDoel = vetDoel;
    }

    // misschien is het slim om een interface hievoor aan te maken.
    @Override
    public Toevoeging addFoodItem(Item item, double hoeveelheidInGram){
        Toevoeging toevoeging = new MaaltijdToevoeging(hoeveelheidInGram, item);
        this.toevoegingen.add(toevoeging);
        return toevoeging;
    }

    @Override
    public void verwijderItem(Toevoeging toevoeging) {
        this.toevoegingen.remove(toevoeging);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public double getGebruikteCalorieen() {
        double toReturn = 0;
        for (Toevoeging toevoeging : toevoegingen) {
            toReturn += toevoeging.getCalorieWaarde();
        }
        return toReturn;
    }

    @Override
    public double getGebruikteKoolhydraten() {
        double toReturn = 0;
        for (Toevoeging toevoeging : toevoegingen) {
            toReturn += toevoeging.getKoolhydraatWaarde();
        }
        return toReturn;
    }

    @Override
    public double getGebruikteEiwitten() {
        double toReturn = 0;
        for (Toevoeging toevoeging : toevoegingen) {
            toReturn += toevoeging.getEiwitWaarde();
        }
        return toReturn;
    }

    @Override
    public double getGebruikteVetten() {
        double toReturn = 0;
        for (Toevoeging toevoeging : toevoegingen) {
            toReturn += toevoeging.getVetWaarde();
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return "naam: " + naam +
                ", calorieDoel: " + calorieDoel +
                ", koolhydraatDoel: " + koolhydraatDoel +
                ", eiwitDoel: " + eiwitDoel +
                ", vetDoel: " + vetDoel;
    }

    @Override
    public void addToevoeging(Toevoeging toevoeging) {
        this.toevoegingen.add(toevoeging);
    }
}
