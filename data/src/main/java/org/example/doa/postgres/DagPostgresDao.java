package org.example.doa.postgres;

import main.java.org.example.models.Dag;
import main.java.org.example.models.DateProvider;
import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.StandaardDag;
import org.example.dbConncetion.DBconnection;
import org.example.doa.DagDao;
import org.example.doa.MaaltijdDoa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DagPostgresDao implements DagDao {

    private final DBconnection dBconnection;
    private final MaaltijdDoa maaltijdDoa;
    private final DateProvider dateProvider;

    public DagPostgresDao(DBconnection dBconnection, MaaltijdDoa maaltijdDoa, DateProvider dateProvider) {
        this.dBconnection = dBconnection;
        this.maaltijdDoa = maaltijdDoa;
        this.dateProvider = dateProvider;
    }

    @Override
    public List<Dag> getAllDagen() throws SQLException{
        String sql = "SELECT * FROM dag";
        List<Dag> dagen = new ArrayList<>();
        try(Connection conn = dBconnection.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                LocalDate date = resultSet.getDate("datum").toLocalDate();
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                List<Maaltijd> maaltijden = maaltijdDoa.getMaaltijdenPerDag(date);
                Dag dag = new StandaardDag(dateProvider,maaltijden,date,cal,kol,eiw,vet);
                dagen.add(dag);
            }

        }
        return dagen;
    }

    @Override
    public void storeDag(Dag dag) throws SQLException{
        String sql = "INSERT INTO dag (datum, caloriedoel, koolhydraatdoel, eiwitdoel, vetdoel) " +
                "VALUES (?,?,?,?,?);";

        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDate(1, Date.valueOf(dag.getDatum()));
            preparedStatement.setDouble(2, dag.getTotaalCalorieDoel());
            preparedStatement.setDouble(3, dag.getTotaalKoolhydraatDoel());
            preparedStatement.setDouble(4, dag.getTotaalEiwitDoel());
            preparedStatement.setDouble(5, dag.getTotaalVetDoel());
            preparedStatement.executeUpdate();

            for(Maaltijd maaltijd : dag.getMaaltijden()){
                maaltijdDoa.storeItem(maaltijd, dag);
            }
        }
    }

    @Override
    public Dag getDagByDate(LocalDate date) throws SQLException {
        String sql = "Select * FROM dag WHERE datum = ?";
        Dag dag = null;
        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDate(1, Date.valueOf(date));

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                LocalDate localDate = resultSet.getDate("datum").toLocalDate();
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                List<Maaltijd> maaltijden = maaltijdDoa.getMaaltijdenPerDag(date);
                dag = new StandaardDag(dateProvider,maaltijden,localDate,cal,kol,eiw,vet);
            }
        }
        return dag;
    }


    @Override
    public Dag getToday() throws SQLException {
        String sql = "Select * FROM dag WHERE datum = ?";
        Dag dag = null;
        try(Connection connection = dBconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                LocalDate localDate = resultSet.getDate("datum").toLocalDate();
                double cal = resultSet.getDouble("caloriedoel");
                double kol = resultSet.getDouble("koolhydraatdoel");
                double eiw = resultSet.getDouble("eiwitdoel");
                double vet = resultSet.getDouble("vetdoel");
                List<Maaltijd> maaltijden = maaltijdDoa.getMaaltijdenPerDag(LocalDate.now());
                dag = new StandaardDag(dateProvider,maaltijden,localDate,cal,kol,eiw,vet);
            }

        }
        return dag;
    }
}
