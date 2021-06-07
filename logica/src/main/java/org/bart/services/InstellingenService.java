package org.bart.services;

import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.Maaltijd;
import org.example.doa.DagInstellingenDao;
import org.example.doa.InstellingMaaltijdDao;

import java.sql.SQLException;
import java.util.List;

public class InstellingenService {

    private final DagInstellingenDao dagInstellingenDao;
    private final InstellingMaaltijdDao instellingMaaltijdDao;

    public InstellingenService(DagInstellingenDao dagInstellingenDao, InstellingMaaltijdDao instellingMaaltijdDao) {
        this.dagInstellingenDao = dagInstellingenDao;
        this.instellingMaaltijdDao = instellingMaaltijdDao;

    }

    public DagInstellingen getInstellingen() throws SQLException {
        return dagInstellingenDao.getInstellingen();
    }

    public List<Maaltijd> getMaaltijden() throws SQLException {
        DagInstellingen dagInstellingen = getInstellingen();
        return dagInstellingen.getMaaltijden();
    }

    public void verwijderMaaltijd(Maaltijd maaltijd) throws SQLException {
        instellingMaaltijdDao.verwijderInstellingMaaltijd(getInstellingen(), maaltijd);
    }

    public void updateInstellingen (DagInstellingen dagInstellingen) throws SQLException {
        dagInstellingenDao.updateInstellingen(dagInstellingen);
    }


}
