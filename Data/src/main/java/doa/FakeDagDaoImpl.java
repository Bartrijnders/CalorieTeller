package doa;

import models.Dag;
import models.DateProvider;
import models.StandaardDag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDagDaoImpl implements DagDao{

    private List<Dag> dagen;

    public FakeDagDaoImpl(DateProvider dateProvider, DagInstellingenDao dagInstellingenDao) {
        this.dagen = new ArrayList<>();
        Dag dag1 = new StandaardDag(dateProvider,dagInstellingenDao.getInstellingen());
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
}
