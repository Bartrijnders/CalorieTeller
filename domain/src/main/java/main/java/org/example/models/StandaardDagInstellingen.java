package main.java.org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandaardDagInstellingen implements DagInstellingen {
    private double calorieDoel;
    private double koolhydraatDoel;
    private double eiwitDoel;
    private double vetDoel;
    private List<Maaltijd> maaltijden;
    private final UUID id;

    public StandaardDagInstellingen(UUID id ,double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel, List<Maaltijd> maaltijden) {
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.maaltijden = maaltijden;
        this.vetDoel = vetDoel;
        this.id = id;
    }

    public StandaardDagInstellingen(UUID id ,double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.maaltijden = new ArrayList<>();
        this.vetDoel = vetDoel;
        this.id = id;
    }

    public StandaardDagInstellingen() {
        id = UUID.randomUUID();
        this.maaltijden = new ArrayList<>();
        this.calorieDoel = 2000;
        this.koolhydraatDoel = 100;
        this.eiwitDoel = 60;
        this.vetDoel = 100;
        Maaltijd ontbijt = new StandaardMaaltijd("Ontbijt",800,20,20,30);
        Maaltijd lunch = new StandaardMaaltijd("Lunch",800,20,20,30);
        Maaltijd diner = new StandaardMaaltijd("Diner",800,20,20,30);
        maaltijden.add(ontbijt);
        maaltijden.add(lunch);
        maaltijden.add(diner);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setMaaltijden(List<Maaltijd> maaltijden) {
        this.maaltijden = maaltijden;
    }

    @Override
    public double getVetDoel() {
        return vetDoel;
    }

    @Override
    public void setVetDoel(double vetDoel) {
        this.vetDoel = vetDoel;
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
    public void addMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel){
        Maaltijd maaltijd = new StandaardMaaltijd(naam, calorieDoel, koolhydraatDoel, eiwitDoel, vetDoel);
        this.maaltijden.add(maaltijd);
    }

    @Override
    public List<Maaltijd> getMaaltijden() {
        return maaltijden;
    }
}
