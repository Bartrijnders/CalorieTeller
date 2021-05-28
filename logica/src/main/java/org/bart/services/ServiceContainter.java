package org.bart.services;

import org.example.doa.DagDao;
import org.example.doa.DagInstellingenDao;
import org.example.doa.ItemDao;
import org.example.doa.MaaltijdDoa;
import org.example.doa.fakes.FakeDagDaoImpl;
import org.example.doa.fakes.FakeDagInstellingenImpl;
import org.example.doa.fakes.FakeItemDaoImpl;
import org.example.doa.fakes.FakeMaaltijdDaoImpl;
import org.example.models.DateProvider;
import org.example.models.ItemValidator;
import org.example.models.ItemValidator100g;
import org.example.models.SystemDateProvider;

import java.time.Clock;

public class ServiceContainter {

    private final ItemCollectie foodItemCollectie;
    private final MaaltijdService maaltijdService;
    private final StandaardDagCollectie standaardDagCollectie;
    private final ItemDao itemDao;
    private final ItemValidator itemValidator;
    private final MaaltijdDoa maaltijdDoa;
    private final DagDao dagDao;
    private final DagInstellingenDao dagInstellingenDao;
    private  final DateProvider dateProvider;

    public ServiceContainter() {
        itemDao = new FakeItemDaoImpl();
        itemValidator = new ItemValidator100g();
        foodItemCollectie = new FoodItemCollectie(itemDao, itemValidator);


        dagInstellingenDao = new FakeDagInstellingenImpl();
        dateProvider = new SystemDateProvider(Clock.systemDefaultZone());
        dagDao = new FakeDagDaoImpl(dateProvider, dagInstellingenDao);
        maaltijdDoa = new FakeMaaltijdDaoImpl(dagDao);
        maaltijdService = new MaaltijdService(maaltijdDoa, itemDao);

        standaardDagCollectie = new StandaardDagCollectie(dagDao, maaltijdDoa);
    }

    public ItemCollectie getFoodItemCollectie() {
        return foodItemCollectie;
    }

    public MaaltijdService getMaaltijdService() {
        return maaltijdService;
    }

    public StandaardDagCollectie getStandaardDagCollectie() {
        return standaardDagCollectie;
    }

    public ItemDao getItemDoa() {
        return itemDao;
    }

    public ItemValidator getItemValidator() {
        return itemValidator;
    }

    public MaaltijdDoa getMaaltijdDoa() {
        return maaltijdDoa;
    }

    public DagDao getDagDao() {
        return dagDao;
    }

    public DagInstellingenDao getDagInstellingenDao() {
        return dagInstellingenDao;
    }

    public DateProvider getDateProvider() {
        return dateProvider;
    }
}
