package org.example.doa.postgres;

import org.example.dbConncetion.DBconnection;
import org.example.doa.ItemDoa;
import org.example.doa.ToevoegingDao;
import org.example.models.Item;
import org.example.models.Maaltijd;
import org.example.models.MaaltijdToevoeging;
import org.example.models.Toevoeging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToevoegingPostgresDao implements ToevoegingDao {


    private final DBconnection dBconnection;
    private final ItemDoa itemDoa;

    public ToevoegingPostgresDao(DBconnection dBconnection, ItemDoa itemDoa) {
        this.dBconnection = dBconnection;
        this.itemDoa = itemDoa;
    }

    @Override
    public List<Toevoeging> getAllToevoegingen(Maaltijd maaltijd) {

        String sql = "SELECT * FROM toevoeging WHERE maaltijdid::text = ?";
        List<Toevoeging> toevoegingen  = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, maaltijd.getId().toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                int hig = resultSet.getInt("hoeveelheidingram");
                UUID itemID = UUID.fromString(resultSet.getString("itemid"));
                Item item = itemDoa.getItemByID(itemID);
                Toevoeging toevoeging = new MaaltijdToevoeging(hig, item, id);
                maaltijd.addToevoeging(toevoeging);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void storeToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) {

    }

    @Override
    public void deleteToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) {

    }

    @Override
    public Toevoeging getByID(UUID id, Maaltijd maaltijd) {
        return null;
    }
}