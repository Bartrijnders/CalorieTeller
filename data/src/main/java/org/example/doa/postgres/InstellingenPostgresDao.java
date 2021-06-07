package org.example.doa.postgres;

import main.java.org.example.models.Maaltijd;
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

    @Override
    public void updateInstellingen(DagInstellingen dagInstellingen) throws SQLException {
        String sql = "UPDATE daginstellingen SET caloriedoel = ?, koolhydraatdoel = ?, eiwitdoel = ?, vetdoel = ? WHERE id::text = ?";

        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDouble(1, dagInstellingen.getCalorieDoel());
            preparedStatement.setDouble(2, dagInstellingen.getKoolhydraatDoel());
            preparedStatement.setDouble(3, dagInstellingen.getEiwitDoel());
            preparedStatement.setDouble(4, dagInstellingen.getVetDoel());
            preparedStatement.setString(5, dagInstellingen.getId().toString());
            preparedStatement.executeUpdate();
        }


        sql = "DELETE FROM maaltijd WHERE daginstellingenid::text = ?";

            try(Connection connection = dBconnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dagInstellingen.getId().toString());
                preparedStatement.executeUpdate();
            }

        sql = "INSERT INTO maaltijd (id, naam, caloriedoel, koolhydraatdoel, eiwitdoel, vetdoel, daginstellingenid) values  (?,?,?,?,?,?,?)";
        for(Maaltijd maaltijd : dagInstellingen.getMaaltijden()){
            try(Connection connection = dBconnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                fillPrepStatement(maaltijd, preparedStatement);
                preparedStatement.setString(7, dagInstellingen.getId().toString());
                preparedStatement.executeUpdate();
            }
        }



    }

    static void fillPrepStatement(Maaltijd maaltijd, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setObject(1, maaltijd.getId());
        preparedStatement.setString(2, maaltijd.getNaam());
        preparedStatement.setDouble(3, maaltijd.getCalorieDoel());
        preparedStatement.setDouble(4, maaltijd.getKoolhydraatDoel());
        preparedStatement.setDouble(5, maaltijd.getEiwitDoel());
        preparedStatement.setDouble(6, maaltijd.getVetDoel());
    }
}
