package doa.fakes;

import doa.DagDao;
import doa.MaaltijdDoa;
import models.Dag;
import models.Maaltijd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeMaaltijdDaoImpl implements MaaltijdDoa {

    private DagDao dagDao;

    public FakeMaaltijdDaoImpl(DagDao dagDao) {

    }

    @Override
    public List<Maaltijd> getAllItems() {
        List<Maaltijd> toReturn = new ArrayList<>();
        for(Dag dag : dagDao.getAllDagen()){
            toReturn.addAll(dag.getMaaltijden());
        }
        return toReturn;
    }

    @Override
    public void deleteItem(Maaltijd maaltijd, Dag dag) {
        dag.getMaaltijden().remove(maaltijd);
    }

    @Override
    public void storeItem(Maaltijd maaltijd, Dag dag) {
        dag.getMaaltijden().add(maaltijd);
    }

    @Override
    public List<Maaltijd> getMaaltijdenPerDag(LocalDate date) {
        return dagDao.getDagByDate(date).getMaaltijden();
    }
}
