package org.example.dbConncetion;

import org.example.doa.postgres.ItemPostgresDao;
import org.example.models.FoodItem;
import org.example.models.Item;

import java.util.List;
import java.util.UUID;

public class test {

    public static void main(String[] args) {
        PostgresCon postgresCon = new PostgresCon();
        ItemPostgresDao itemPostgresDao = new ItemPostgresDao(postgresCon);


    }
}
