package DTO;

import models.Maaltijd;
import models.Toevoeging;

import java.util.List;

public class StandaardMaaltijdDTO implements MaaltijdDTO {

    private Maaltijd maaltijd;

    public StandaardMaaltijdDTO(Maaltijd maaltijd) {
        this.maaltijd = maaltijd;
    }

    @Override
    public List<Toevoeging> getToevoegingen(){
        return maaltijd.getToevoegingen();
    }

    @Override
    public String getNaam(){
        return maaltijd.getNaam();
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
