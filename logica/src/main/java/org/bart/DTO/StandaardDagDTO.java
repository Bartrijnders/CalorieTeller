package org.bart.DTO;

import main.java.org.example.models.Dag;
import main.java.org.example.models.Maaltijd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StandaardDagDTO implements DagDTO {

    private Dag dag;

    public StandaardDagDTO(Dag dag) {
        this.dag = dag;
    }

    @Override
    public List<MaaltijdDTO> getAllMaaltijden(){
        List<MaaltijdDTO> toReturn = new ArrayList<>();
        for(Maaltijd maaltijd : dag.getMaaltijden()){
            toReturn.add(new StandaardMaaltijdDTO(maaltijd));
        }
        return toReturn;
    }

    @Override
    public double getCalorieDoel() {
        return this.dag.getTotaalCalorieDoel();
    }

    @Override
    public double getKoolhydraatDoel(){
        return this.dag.getTotaalKoolhydraatDoel();
    }

    @Override
    public double getEiwitDoel(){
        return this.dag.getTotaalEiwitDoel();
    }

    @Override
    public double getVetDoel(){
        return this.dag.getTotaalVetDoel();
    }

    @Override
    public LocalDate getDatum(){
        return this.dag.getDatum();
    }

    @Override
    public Dag getDag() {
        return dag;
    }
}
