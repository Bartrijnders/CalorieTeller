package org.example.doa.postgres;

import org.example.dbConncetion.DBconnection;
import org.example.doa.ItemDoa;
import org.example.models.FoodItem;
import org.example.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemPostgresDao implements ItemDoa {

    private final DBconnection dBconnection;

    public ItemPostgresDao(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
    }

    @Override
    public List<Item> getAllItems() {
        String sql = "SELECT * FROM item";
        List<Item> items = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriewaarde");
                double kol = resultSet.getDouble("koolhydraatwaarde");
                double eiw = resultSet.getDouble("eiwitwaarde");
                double vet = resultSet.getDouble("vetwaarde");
                FoodItem foodItem = new FoodItem(naam, cal, vet, kol, eiw, id);
                items.add(foodItem);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }

    @Override
    public void deleteItem(Item item) {
        String sql = "DELETE FROM item WHERE id::text = ?";

        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, item.getID().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void storeItem(Item item) {
        String sql = "INSERT INTO item(id, naam, caloriewaarde,koolhydraatwaarde, eiwitwaarde, vetwaarde) " +
                "VALUES (?,?,?,?,?,?)";

        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setObject(1, item.getID());
            preparedStatement.setString(2, item.getNaam());
            preparedStatement.setDouble(3, item.getCalorieWaarde());
            preparedStatement.setDouble(4, item.getKoolhydraatWaarde());
            preparedStatement.setDouble(5, item.getEiwitWaarde());
            preparedStatement.setDouble(6, item.getVetWaarde());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Item getItemByID(UUID id) {
        String sql = "SELECT * FROM item WHERE id::text = ?";
        Item toReturn = null;
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UUID uuid = UUID.fromString(resultSet.getString("id"));
                String naam = resultSet.getString("naam");
                double cal = resultSet.getDouble("caloriewaarde");
                double kol = resultSet.getDouble("koolhydraatwaarde");
                double eiw = resultSet.getDouble("eiwitwaarde");
                double vet = resultSet.getDouble("vetwaarde");
                toReturn = new FoodItem(naam, cal, vet, kol, eiw, uuid);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return toReturn ;
    }
}
