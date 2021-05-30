package org.example.doa.fakes;

import org.example.doa.DagDao;
import org.example.doa.DagInstellingenDao;
import main.java.org.example.models.Dag;
import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.DateProvider;
import main.java.org.example.models.StandaardDag;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDagDaoImpl implements DagDao {

    private List<Dag> dagen;
    private DateProvider dateProvider;
    private DagInstellingen dagInstellingen;

    public FakeDagDaoImpl(DateProvider dateProvider, DagInstellingenDao dagInstellingenDao) throws SQLException {
        this.dagen = new ArrayList<>();
        this.dateProvider = dateProvider;
        this.dagInstellingen = dagInstellingenDao.getInstellingen();
        Dag dag1 = new StandaardDag(dateProvider,dagInstellingen);
        dagen.add(dag1);
    }

    @Override
    public List<Dag> getAllDagen() {
        return dagen;
    }

    @Override
    public void storeDag(Dag dag) {
        dagen.add(dag);
    }

    @Override
    public Dag getDagByDate(LocalDate date) {
        return dagen.stream().filter(x -> x.getDatum() == date).findFirst().orElse(null);
    }

    @Override
    public Dag getToday() {
        Dag toReturn = dagen.stream().filter(x -> x.getDatum() == dateProvider.GetCurrentDate()).findFirst().orElse(null);
        if(toReturn == null){
            toReturn = new StandaardDag(dateProvider, dagInstellingen);
            dagen.add(toReturn);
        }
        return toReturn;
    }
}
