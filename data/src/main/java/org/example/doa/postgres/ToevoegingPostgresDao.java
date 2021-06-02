package org.example.doa.postgres;

import org.example.dbConncetion.DBconnection;
import org.example.doa.ItemDao;
import org.example.doa.ToevoegingDao;
import main.java.org.example.models.Item;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.MaaltijdToevoeging;
import main.java.org.example.models.Toevoeging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToevoegingPostgresDao implements ToevoegingDao {


    private final DBconnection dBconnection;
    private final ItemDao itemDao;

    public ToevoegingPostgresDao(DBconnection dBconnection, ItemDao itemDao) {
        this.dBconnection = dBconnection;
        this.itemDao = itemDao;
    }

    @Override
    public List<Toevoeging> getAllToevoegingen(Maaltijd maaltijd) throws SQLException {

        String sql = "SELECT * FROM toevoeging WHERE maaltijdid::text = ?;";
        List<Toevoeging> toevoegingen  = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, maaltijd.getId().toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                int hig = resultSet.getInt("hoeveelheidingram");
                UUID itemID = UUID.fromString(resultSet.getString("itemid"));
                Item item = itemDao.getItemByID(itemID);
                Toevoeging toevoeging = new MaaltijdToevoeging(hig, item, id);
                maaltijd.addToevoeging(toevoeging);
            }

        }

        return toevoegingen;
    }

    @Override
    public void storeToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) throws SQLException{
        String sql = "INSERT INTO toevoeging (id, hoeveelheidingram, maaltijdid, itemid) " +
                "VALUES (?,?,?,?);";

        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setObject(1, toevoeging.getID());
            preparedStatement.setDouble(2, toevoeging.getHoeveelheidInGram());
            preparedStatement.setObject(3, maaltijd.getId());
            preparedStatement.setObject(4,toevoeging.getItem().getID());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void deleteToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) {
        String sql = "DELETE FROM toevoeging WHERE id::text = ?";

        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setObject(1, toevoeging.getID().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Toevoeging getByID(UUID id, Maaltijd maaltijd) throws SQLException {
        String sql = "SELECT * FROM toevoeging WHERE id::text = ?;";
        Toevoeging toevoeging = null;
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, maaltijd.getId().toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UUID uuid = UUID.fromString(resultSet.getString("id"));
                int hig = resultSet.getInt("hoeveelheidingram");
                UUID itemID = UUID.fromString(resultSet.getString("itemid"));
                Item item = itemDao.getItemByID(itemID);
                toevoeging = new MaaltijdToevoeging(hig, item, uuid);
            }

        }

        return toevoeging;
    }
}
