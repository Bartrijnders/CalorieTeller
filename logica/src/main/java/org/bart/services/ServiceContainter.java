package org.bart.services;

import org.example.dbConncetion.DBconnection;
import org.example.dbConncetion.PostgresCon;
import org.example.doa.*;
import main.java.org.example.models.DateProvider;
import main.java.org.example.models.ItemValidator;
import main.java.org.example.models.ItemValidator100g;
import main.java.org.example.models.SystemDateProvider;
import org.example.doa.postgres.*;

import java.sql.SQLException;
import java.time.Clock;

public class ServiceContainter {

    private final ItemCollectie foodItemCollectie;
    private final MaaltijdService maaltijdService;
    private final StandaardDagService standaardDagService;
    private final ItemDao itemDao;
    private final ItemValidator itemValidator;
    private final MaaltijdDoa maaltijdDoa;
    private final DagDao dagDao;
    private final DagInstellingenDao dagInstellingenDao;
    private  final DateProvider dateProvider;
    private final DBconnection dBconnection;
    private final ToevoegingDao toevoegingDao;
    private final InstellingMaaltijdDao instellingMaaltijdDao;

    public ServiceContainter() throws SQLException {
        dBconnection = new PostgresCon();
        itemDao = new ItemPostgresDao(dBconnection);
        itemValidator = new ItemValidator100g();
        foodItemCollectie = new FoodItemCollectie(itemDao, itemValidator);
        toevoegingDao = new ToevoegingPostgresDao(dBconnection, itemDao);
        maaltijdDoa = new MaaltijdPostgresDao(dBconnection, toevoegingDao);
        instellingMaaltijdDao = (InstellingMaaltijdDao) maaltijdDoa;
        dagInstellingenDao = new InstellingenPostgresDao(dBconnection, instellingMaaltijdDao);
        dateProvider = new SystemDateProvider(Clock.systemDefaultZone());
        dagDao = new DagPostgresDao(dBconnection, maaltijdDoa, dateProvider);
        maaltijdService = new MaaltijdService(maaltijdDoa, itemDao, toevoegingDao);
        standaardDagService = new StandaardDagService(dagDao, maaltijdDoa,dateProvider,dagInstellingenDao);
    }

    public ItemCollectie getFoodItemCollectie() {
        return foodItemCollectie;
    }

    public MaaltijdService getMaaltijdService() {
        return maaltijdService;
    }

    public StandaardDagService getStandaardDagCollectie() {
        return standaardDagService;
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
