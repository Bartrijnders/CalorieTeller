package doa.fakes;

import doa.DagDao;
import doa.DagInstellingenDao;
import models.Dag;
import models.DagInstellingen;
import models.DateProvider;
import models.StandaardDag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDagDaoImpl implements DagDao {

    private List<Dag> dagen;
    private DateProvider dateProvider;
    private DagInstellingen dagInstellingen;

    public FakeDagDaoImpl(DateProvider dateProvider, DagInstellingenDao dagInstellingenDao) {
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
        return dagen.stream().filter(x -> x.getDatum() == dateProvider.GetCurrentDate()).findFirst().orElse(new StandaardDag(dateProvider,dagInstellingen));
    }
}
