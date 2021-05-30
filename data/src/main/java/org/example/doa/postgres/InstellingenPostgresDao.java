package org.example.doa.postgres;

import org.example.dbConncetion.DBconnection;
import org.example.doa.DagInstellingenDao;
import org.example.doa.InstellingMaaltijdDao;
import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.StandaardDagInstellingen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class InstellingenPostgresDao implements DagInstellingenDao {

    private final DBconnection dBconnection;
    private final InstellingMaaltijdDao instellingMaaltijdDao;

    public InstellingenPostgresDao(DBconnection dBconnection, InstellingMaaltijdDao instellingMaaltijdDao) {
        this.dBconnection = dBconnection;
        this.instellingMaaltijdDao = instellingMaaltijdDao;
    }

    @Override
    public DagInstellingen getInstellingen() throws SQLException {
        String sql = "SELECT * from daginstellingen LIMIT 1";
        DagInstellingen dagInstellingen = null;

        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                dagInstellingen = new StandaardDagInstellingen(id, cal, kol, eiw, vet);
                dagInstellingen.setMaaltijden(instellingMaaltijdDao.getInstellingsMaaltijden(dagInstellingen));

            }
        }
        return dagInstellingen;
    }
}
