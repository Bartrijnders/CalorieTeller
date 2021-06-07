package org.example.doa.fakes;

import org.example.doa.DagInstellingenDao;
import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.StandaardDagInstellingen;

import java.sql.SQLException;

public class FakeDagInstellingenImpl implements DagInstellingenDao {

    private DagInstellingen instellingen;

    public FakeDagInstellingenImpl() {
        this.instellingen = new StandaardDagInstellingen();
    }

    @Override
    public DagInstellingen getInstellingen() {
        return instellingen;
    }

    @Override
    public void updateInstellingen(DagInstellingen dagInstellingen) throws SQLException {
        this.instellingen = dagInstellingen;
    }
}
