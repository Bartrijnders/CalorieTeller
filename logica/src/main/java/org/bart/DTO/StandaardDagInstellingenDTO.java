package org.bart.DTO;

import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.Maaltijd;

import java.util.ArrayList;
import java.util.List;

public class StandaardDagInstellingenDTO implements DagInstellingenDTO {

    private DagInstellingen dagInstellingen;

    public StandaardDagInstellingenDTO(DagInstellingen dagInstellingen) {
        this.dagInstellingen = dagInstellingen;
    }

    @Override
    public Double getCalorieDoel(){
        return dagInstellingen.getCalorieDoel();
    }
    @Override
    public Double getKoolhydraatDoel(){
        return dagInstellingen.getKoolhydraatDoel();
    }
    @Override
    public Double getEiwitDoel(){
        return dagInstellingen.getEiwitDoel();
    }
    @Override
    public Double getVetdoel(){
        return dagInstellingen.getVetDoel();
    }

    @Override
    public List<MaaltijdDTO> getMaaltijden(){
        List<MaaltijdDTO> dtos = new ArrayList<>();
        for(Maaltijd maaltijd : dagInstellingen.getMaaltijden()){
             dtos.add(new StandaardMaaltijdDTO(maaltijd));
        }
        return dtos;
    }
}
