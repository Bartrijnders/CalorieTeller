package doa.fakes;

import doa.DagInstellingenDao;
import models.DagInstellingen;
import models.StandaardDagInstellingen;

public class FakeDagInstellingenImpl implements DagInstellingenDao {

    private DagInstellingen instellingen;

    public FakeDagInstellingenImpl() {
        this.instellingen = new StandaardDagInstellingen();
    }

    @Override
    public DagInstellingen getInstellingen() {
        return instellingen;
    }
}
