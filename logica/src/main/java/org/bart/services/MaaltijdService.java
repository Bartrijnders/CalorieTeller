package org.bart.services;

import org.example.doa.ItemDao;
import org.example.doa.MaaltijdDoa;
import org.example.doa.ToevoegingDao;
import org.example.doa.fakes.FakeToevoegingDaoImpl;
import org.example.models.Dag;
import org.example.models.Item;
import org.example.models.Maaltijd;
import org.example.models.Toevoeging;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MaaltijdService {

    private final MaaltijdDoa maaltijdDoa;
    private final ItemDao itemDao;
    private final ToevoegingDao toevoegingDao;

    public MaaltijdService(MaaltijdDoa maaltijdDoa, ItemDao itemDao) {
        this.maaltijdDoa = maaltijdDoa;
        this.itemDao = itemDao;
        this.toevoegingDao = new FakeToevoegingDaoImpl();
    }

    public List<Maaltijd> maaltijdenVanDagOphalen(Dag dag) throws SQLException {
        return maaltijdDoa.getMaaltijdenPerDag(dag.getDatum());
    }

    public void addItem(UUID maaltijdId, UUID itemId, LocalDate date, double hoeveelheidInGram) throws SQLException {
        Item item = itemDao.getItemByID(itemId);
        Maaltijd maaltijd = maaltijdDoa.getMaaltijd(maaltijdId,date);
        maaltijd.addFoodItem(item, hoeveelheidInGram);
    }

    public void removeItem(UUID maaltijdId, UUID toevoegingsId, LocalDate date) throws SQLException {
        Maaltijd maaltijd = maaltijdDoa.getMaaltijd(maaltijdId,date);
        Toevoeging toevoeging = toevoegingDao.getByID(toevoegingsId, maaltijd);
        toevoegingDao.deleteToevoeging(toevoeging, maaltijd);

    }
}
