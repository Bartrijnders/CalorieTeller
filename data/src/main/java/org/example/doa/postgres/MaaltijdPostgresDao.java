package org.example.doa.postgres;

import main.java.org.example.models.Dag;
import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.StandaardMaaltijd;
import org.example.dbConncetion.DBconnection;
import org.example.doa.InstellingMaaltijdDao;
import org.example.doa.MaaltijdDoa;
import org.example.doa.ToevoegingDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MaaltijdPostgresDao implements MaaltijdDoa , InstellingMaaltijdDao {

    private final DBconnection dBconnection;
    private final ToevoegingDao toevoegingDao;

    public MaaltijdPostgresDao(DBconnection dBconnection, ToevoegingDao toevoegingDao) {
        this.dBconnection = dBconnection;
        this.toevoegingDao = toevoegingDao;
    }

    @Override
    public List<Maaltijd> getAllItems() throws SQLException {

        String sql = "SELECT * FROM maaltijd";
        List<Maaltijd> maaltijden  = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                Maaltijd maaltijd = new StandaardMaaltijd(id, naam, cal, kol, eiw , vet);
                toevoegingDao.getAllToevoegingen(maaltijd);
                maaltijden.add(maaltijd);
            }

        }

        return maaltijden;
    }

    @Override
    public void deleteItem(Maaltijd maaltijd, Dag dag) throws SQLException{
        String sql = "DELETE FROM maaltijd WHERE id::text = ?";

        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, maaltijd.getId().toString());

            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void storeItem(Maaltijd maaltijd, Dag dag) throws SQLException {
        String sql = "INSERT INTO maaltijd (id, naam, caloriedoel, koolhydraatdoel, eiwitdoel, vetdoel, dagdatum) " +
                "VALUES (?,?,?,?,?,?,?)";

        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            InstellingenPostgresDao.fillPrepStatement(maaltijd, preparedStatement);
            preparedStatement.setDate(7, Date.valueOf(dag.getDatum()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Maaltijd> getMaaltijdenPerDag(LocalDate date)  throws SQLException{

        String sql = "SELECT * FROM maaltijd WHERE dagdatum = ?;";
        List<Maaltijd> maaltijden  = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                Maaltijd maaltijd = new StandaardMaaltijd(id, naam, cal, kol, eiw , vet);
                toevoegingDao.getAllToevoegingen(maaltijd);
                maaltijden.add(maaltijd);
            }

        }
        return maaltijden;
    }

    @Override
    public Maaltijd getMaaltijd(UUID id, LocalDate date) throws SQLException {
        String sql = "SELECT * FROM maaltijd WHERE id::text = ?;";
        Maaltijd maaltijd  = null;
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UUID uuid = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                maaltijd = new StandaardMaaltijd(uuid, naam, cal, kol, eiw , vet);
                toevoegingDao.getAllToevoegingen(maaltijd);
            }

        }
        return maaltijd;
    }

    @Override
    public List<Maaltijd> getInstellingsMaaltijden(DagInstellingen dagInstellingen) throws SQLException {

        String sql = "SELECT * FROM maaltijd WHERE daginstellingenid::text = ?";
        List<Maaltijd> maaltijden  = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, dagInstellingen.getId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                Maaltijd maaltijd = new StandaardMaaltijd(id, naam, cal, kol, eiw , vet);
                maaltijden.add(maaltijd);
            }

        }
        return maaltijden;
    }

    @Override
    public void verwijderInstellingMaaltijd(DagInstellingen dagInstellingen, Maaltijd maaltijd) throws SQLException {
        String sql = "DELETE FROM maaltijd WHERE daginstellingenid::text = ? AND id::text = ?";

        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, dagInstellingen.getId().toString());
            preparedStatement.setString(2, maaltijd.getId().toString());
            preparedStatement.executeUpdate();
        }
    }
}
