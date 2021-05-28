package org.bart.services;

import org.bart.DTO.DagDTO;
import org.bart.DTO.MaaltijdDTO;
import org.bart.DTO.StandaardDagDTO;
import org.bart.DTO.StandaardMaaltijdDTO;
import org.example.doa.DagDao;
import org.example.doa.MaaltijdDoa;
import org.example.models.Dag;
import org.example.models.Maaltijd;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StandaardDagCollectie {

    private List<Dag> dagen;
    private DagDao dagDao;
    private MaaltijdDoa maaltijdDoa;

    public StandaardDagCollectie(DagDao dagDao, MaaltijdDoa maaltijdDoa) throws SQLException {
        this.dagDao = dagDao;
        this.dagen = dagDao.getAllDagen();
        this.maaltijdDoa = maaltijdDoa;
    }

    public DagDTO getToday() throws SQLException {
        return new StandaardDagDTO(dagDao.getToday());
    }

    public MaaltijdDTO maaltijdAanDagToevoegen(LocalDate date, String naam, double calorieWaarde, double koolhydraatWaarde, double eiwitWaarde, double vetWaarde) throws SQLException {
        Dag dag = dagDao.getDagByDate(date);
        Maaltijd maaltijd = dag.addMaaltijd(naam, calorieWaarde,koolhydraatWaarde,eiwitWaarde,vetWaarde);
        maaltijdDoa.storeItem(maaltijd,dag);
        return new StandaardMaaltijdDTO(maaltijd);
    }

    public void maaltijdVerwijderen(LocalDate date, UUID maaltijdId) throws SQLException {
        Maaltijd maaltijd = maaltijdDoa.getMaaltijd(maaltijdId, date);
        Dag dag = dagDao.getDagByDate(date);
        dag.verwijderMaaltijd(maaltijd);
    }


}
