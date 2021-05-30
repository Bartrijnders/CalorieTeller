package org.bart.DTO;

import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandaardMaaltijdDTO implements MaaltijdDTO {

    private Maaltijd maaltijd;

    public StandaardMaaltijdDTO(Maaltijd maaltijd) {
        this.maaltijd = maaltijd;
    }

    @Override
    public List<ToevoegingDTO> getToevoegingen(){
        List<ToevoegingDTO> toReturn  = new ArrayList<>();
        for(Toevoeging toevoeging : maaltijd.getToevoegingen()){
            toReturn.add(new MaaltijdToevoegingDTO(toevoeging));
        }
        return toReturn;
    }

    @Override
    public String getNaam(){
        return maaltijd.getNaam();
    }

    @Override
    public UUID getId() {
        return maaltijd.getId();
    }

    @Override
    public double getCalorieDoel() {
      return  maaltijd.getCalorieDoel();
    }

    @Override
    public double getKoolhydraatDoel(){
        return maaltijd.getKoolhydraatDoel();
    }

    @Override
    public double getEiwitDoel(){
        return maaltijd.getEiwitDoel();
    }

    @Override
    public double getVetDoel() {
        return maaltijd.getVetDoel();
    }

    @Override
    public double getGebruikteCalorieen(){
        return  maaltijd.getGebruikteCalorieen();
    }

    @Override
    public double getGebruikteKoolhydraat(){
        return  maaltijd.getGebruikteKoolhydraten();
    }

    @Override
    public double getGebruikteEiwit(){
        return  maaltijd.getEiwitDoel();
    }

    @Override
    public double getGebruikteVet(){
        return  maaltijd.getGebruikteVetten();
    }

    @Override
    public String toString() {
        return maaltijd.toString();
    }
}
