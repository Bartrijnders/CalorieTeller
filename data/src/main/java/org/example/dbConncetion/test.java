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

        List<Item> items = itemPostgresDao.getAllItems();
        for (Item item : items){
            System.out.println(item.toString());
        }

        Item item = new FoodItem("test", 1,1,1,1, UUID.fromString("20f04a56-1747-41fa-b6fd-92b62949894d"));


        System.out.println();
        System.out.println(itemPostgresDao.getItemByID(UUID.fromString("9f8ce8f7-e4f2-420b-bac6-94c5d5792047")).toString());
    }
}
