package main.java.org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StandaardDag implements Dag {
    private DateProvider dateProvider;
    private List<Maaltijd> maaltijden;
    private LocalDate datum;
    private double totaalCalorieDoel;
    private double totaalKoolhydraatDoel;
    private double totaalEiwitDoel;
    private double totaalVetDoel;

    public StandaardDag(DateProvider dateProvider, List<Maaltijd> maaltijden, LocalDate datum, double totaalCalorieDoel, double totaalKoolhydraatDoel, double totaalEiwitDoel, double totaalVetDoel) {
        this.dateProvider = dateProvider;
        this.maaltijden = maaltijden;
        this.datum = datum;
        this.totaalCalorieDoel = totaalCalorieDoel;
        this.totaalKoolhydraatDoel = totaalKoolhydraatDoel;
        this.totaalEiwitDoel = totaalEiwitDoel;
        this.totaalVetDoel = totaalVetDoel;
    }

    public StandaardDag(DateProvider dateProvider, DagInstellingen dagInstellingen) {
        this.dateProvider = dateProvider;
        //this.maaltijden = dagInstellingen.getMaaltijden();
        this.maaltijden = new ArrayList<>();
        for(Maaltijd maaltijd : dagInstellingen.getMaaltijden()){
            Maaltijd toAdd = new StandaardMaaltijd(maaltijd.getNaam(), maaltijd.getCalorieDoel(), maaltijd.getKoolhydraatDoel(), maaltijd.getEiwitDoel(), maaltijd.getVetDoel());
            maaltijden.add(toAdd);
        }
        this.datum = dateProvider.GetCurrentDate();
        this.totaalCalorieDoel = dagInstellingen.getCalorieDoel();
        this.totaalKoolhydraatDoel = dagInstellingen.getKoolhydraatDoel();
        this.totaalEiwitDoel = dagInstellingen.getEiwitDoel();
        this.totaalVetDoel = dagInstellingen.getVetDoel();
    }

    @Override
    public Maaltijd addMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        Maaltijd maaltijd = new StandaardMaaltijd(naam, calorieDoel, koolhydraatDoel, eiwitDoel, vetDoel);
        this.maaltijden.add(maaltijd);
        return maaltijd;
    }

    @Override
    //test
    public void verwijderMaaltijd(Maaltijd maaltijd){
        this.maaltijden.stream().filter(x -> x.getId().toString().equals(maaltijd.getId().toString())).findFirst().ifPresent(x -> maaltijden.remove(x));
    }

    @Override
    public double getTotaalCalorieDoel() {
        return totaalCalorieDoel;
    }

    @Override
    public void setTotaalCalorieDoel(double totaalCalorieDoel) {
        this.totaalCalorieDoel = totaalCalorieDoel;
    }

    @Override
    public double getTotaalKoolhydraatDoel() {
        return totaalKoolhydraatDoel;
    }

    @Override
    public void setTotaalKoolhydraatDoel(double totaalKoolhydraatDoel) {
        this.totaalKoolhydraatDoel = totaalKoolhydraatDoel;
    }

    @Override
    public double getTotaalEiwitDoel() {
        return totaalEiwitDoel;
    }

    @Override
    public void setTotaalEiwitDoel(double totaalEiwitDoel) {
        this.totaalEiwitDoel = totaalEiwitDoel;
    }

    @Override
    public double getTotaalVetDoel() {
        return totaalVetDoel;
    }

    @Override
    public void setTotaalVetDoel(double totaalVetDoel) {
        this.totaalVetDoel = totaalVetDoel;
    }

    @Override
    public List<Maaltijd> getMaaltijden() {
        return maaltijden;
    }

    @Override
    public LocalDate getDatum() {
        return datum;
    }

    @Override
    public double getUsedKoolhydraten(){
        double result = 0.0;
        for(Maaltijd maaltijd : maaltijden){
            result += maaltijd.getGebruikteKoolhydraten();
        }
        return result;
    }

    @Override
    public double getUsedEiwitten(){
        double result = 0.0;
        for(Maaltijd maaltijd : maaltijden){
            result += maaltijd.getGebruikteEiwitten();
        }
        return result;
    }

    @Override
    public double getUsedVetten(){
        double result = 0.0;
        for(Maaltijd maaltijd : maaltijden){
            result += maaltijd.getGebruikteVetten();
        }
        return result;
    }

    @Override
    public double getUsedCalorieen(){
        double result = 0.0;
        for(Maaltijd maaltijd : maaltijden){
            result += maaltijd.getGebruikteCalorieen();
        }
        return result;
    }
}
