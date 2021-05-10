package DTO;

import models.Dag;
import models.Maaltijd;

import java.time.LocalDate;
import java.util.List;

public class StandaardDagDTO implements DagDTO {

    private Dag dag;

    public StandaardDagDTO(Dag dag) {
        this.dag = dag;
    }

    @Override
    public List<Maaltijd> getAllMaaltijden(){
        return this.dag.getMaaltijden();
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
}
