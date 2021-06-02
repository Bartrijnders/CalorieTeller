package org.bart.services;

import main.java.org.example.models.*;
import org.example.doa.DagDao;
import org.example.doa.DagInstellingenDao;
import org.example.doa.MaaltijdDoa;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StandaardDagService {

    private List<Dag> dagen;
    private DagDao dagDao;
    private MaaltijdDoa maaltijdDoa;
    private DateProvider dateProvider;
    private DagInstellingenDao dagInstellingenDao;

    public StandaardDagService(DagDao dagDao, MaaltijdDoa maaltijdDoa, DateProvider dateProvider, DagInstellingenDao dagInstellingenDao) throws SQLException {
        this.dagDao = dagDao;
        this.dagen = dagDao.getAllDagen();
        this.maaltijdDoa = maaltijdDoa;
        this.dateProvider = dateProvider;
        this.dagInstellingenDao = dagInstellingenDao;
    }

    public Dag getToday() throws SQLException {
        Dag dag = dagDao.getToday();
        if(dag == null){
            DagInstellingen dagInstellingen = dagInstellingenDao.getInstellingen();
            dag = new StandaardDag(dateProvider, dagInstellingen);
            dagDao.storeDag(dag);
        }
        return dag;
    }

    public Maaltijd maaltijdAanDagToevoegen(String naam, double calorieWaarde, double koolhydraatWaarde, double eiwitWaarde, double vetWaarde, Dag dag) throws SQLException {
        Maaltijd maaltijd = dag.addMaaltijd(naam, calorieWaarde,koolhydraatWaarde,eiwitWaarde,vetWaarde);
        maaltijdDoa.storeItem(maaltijd,dag);
        return maaltijd;
    }

    public void maaltijdVerwijderen(Dag dag , Maaltijd maaltijd) throws SQLException {
        dag.verwijderMaaltijd(maaltijd);
        maaltijdDoa.deleteItem(maaltijd, dag);
    }


}
