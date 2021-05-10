package org.bart.services;

import org.example.doa.ItemDoa;
import org.example.doa.MaaltijdDoa;
import org.example.doa.ToevoegingDao;
import org.example.doa.fakes.FakeToevoegingDaoImpl;
import org.example.models.Dag;
import org.example.models.Item;
import org.example.models.Maaltijd;
import org.example.models.Toevoeging;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MaaltijdService {

    private final MaaltijdDoa maaltijdDoa;
    private final ItemDoa itemDoa;
    private final ToevoegingDao toevoegingDao;

    public MaaltijdService(MaaltijdDoa maaltijdDoa, ItemDoa itemDoa) {
        this.maaltijdDoa = maaltijdDoa;
        this.itemDoa = itemDoa;
        this.toevoegingDao = new FakeToevoegingDaoImpl();
    }

    public List<Maaltijd> maaltijdenVanDagOphalen(Dag dag){
        return maaltijdDoa.getMaaltijdenPerDag(dag.getDatum());
    }

    public void addItem(UUID maaltijdId, UUID itemId, LocalDate date, double hoeveelheidInGram){
        Item item = itemDoa.getItemByID(itemId);
        Maaltijd maaltijd = maaltijdDoa.getMaaltijd(maaltijdId,date);
        maaltijd.addFoodItem(item, hoeveelheidInGram);
    }

    public void removeItem(UUID maaltijdId, UUID toevoegingsId, LocalDate date){
        Maaltijd maaltijd = maaltijdDoa.getMaaltijd(maaltijdId,date);
        Toevoeging toevoeging = toevoegingDao.getByID(toevoegingsId, maaltijd);
        toevoegingDao.deleteToevoeging(toevoeging, maaltijd);

    }
}
