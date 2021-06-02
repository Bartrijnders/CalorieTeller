package org.bart.services;

import org.example.doa.ItemDao;
import org.example.doa.MaaltijdDoa;
import org.example.doa.ToevoegingDao;
import org.example.doa.fakes.FakeToevoegingDaoImpl;
import main.java.org.example.models.Dag;
import main.java.org.example.models.Item;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MaaltijdService {

    private final MaaltijdDoa maaltijdDoa;
    private final ItemDao itemDao;
    private final ToevoegingDao toevoegingDao;

    public MaaltijdService(MaaltijdDoa maaltijdDoa, ItemDao itemDao, ToevoegingDao toevoegingDao) {
        this.maaltijdDoa = maaltijdDoa;
        this.itemDao = itemDao;
        this.toevoegingDao = toevoegingDao;
    }

    public List<Maaltijd> maaltijdenVanDagOphalen(Dag dag) throws SQLException {
        return maaltijdDoa.getMaaltijdenPerDag(dag.getDatum());
    }

    public void addItem(Maaltijd maaltijd, Item item, double hoeveelheidInGram) throws SQLException {
        Toevoeging toevoeging = maaltijd.addFoodItem(item, hoeveelheidInGram);
        toevoegingDao.storeToevoeging(toevoeging, maaltijd);
    }

    public void removeItem(Maaltijd maaltijd, Toevoeging toevoeging) throws SQLException {
        maaltijd.verwijderItem(toevoeging);
        toevoegingDao.deleteToevoeging(toevoeging, maaltijd);

    }
}
