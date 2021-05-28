package org.example.doa.fakes;

import org.example.doa.DagDao;
import org.example.doa.MaaltijdDoa;
import org.example.models.Dag;
import org.example.models.Maaltijd;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeMaaltijdDaoImpl implements MaaltijdDoa {

    private DagDao dagDao;

    public FakeMaaltijdDaoImpl(DagDao dagDao) {
        this.dagDao = dagDao;
    }

    @Override
    public List<Maaltijd> getAllItems() throws SQLException {
        List<Maaltijd> toReturn = new ArrayList<>();
        for(Dag dag : dagDao.getAllDagen()){
            toReturn.addAll(dag.getMaaltijden());
        }
        return toReturn;
    }

    @Override
    public void deleteItem (Maaltijd maaltijd, Dag dag) throws SQLException  {
        dag.getMaaltijden().remove(maaltijd);
    }

    @Override
    public void storeItem(Maaltijd maaltijd, Dag dag) throws SQLException {
        dag.getMaaltijden().add(maaltijd);
    }

    @Override
    public List<Maaltijd> getMaaltijdenPerDag(LocalDate date) throws SQLException {
        return dagDao.getDagByDate(date).getMaaltijden();
    }

    @Override
    public Maaltijd getMaaltijd(UUID id, LocalDate date) throws SQLException {
        Dag dag = dagDao.getDagByDate(date);
        return dag.getMaaltijden().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
