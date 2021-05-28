package org.example.doa.postgres;

import org.example.dbConncetion.DBconnection;
import org.example.doa.DagDao;
import org.example.models.Dag;
import org.example.models.FoodItem;
import org.example.models.Item;
import org.example.models.StandaardDag;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DagPostgresDao implements DagDao {

    private final DBconnection dBconnection;

    public DagPostgresDao(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
    }

    @Override
    public List<Dag> getAllDagen() throws SQLException{
        String sql = "SELECT * FROM dag";
        List<Item> items = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                LocalDate date = resultSet.getDate("datum").toLocalDate();
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                //Dag dag = new StandaardDag(date,cal,kol,eiw,vet);
                //items.add(foodItem);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void storeDag(Dag dag) {

    }

    @Override
    public Dag getDagByDate(LocalDate date) {
        return null;
    }

    @Override
    public Dag getToday() {
        return null;
    }
}
