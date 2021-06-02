package org.bart.services;


import org.example.doa.ItemDao;
import main.java.org.example.models.FoodItem;
import main.java.org.example.models.Item;
import main.java.org.example.models.ItemValidator;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodItemCollectie implements ItemCollectie {
    private final ItemValidator itemValidator;
    private final ItemDao itemDao;

    public FoodItemCollectie(ItemDao itemDao, ItemValidator itemValidator) throws SQLException {
        this.itemDao = itemDao;
        this.itemValidator = itemValidator;
    }



    @Override
    public Item foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException, SQLException {
        if(itemValidator.valideerWaardes(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde)){
            Item item = new FoodItem(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde);
            itemDao.storeItem(item);
            return item;
        }
        else return null;
    }

    @Override
    public void ItemVerwijderen(Item item) throws SQLException {
        itemDao.deleteItem(item);
    }

    @Override
    public List<Item> getItems() throws SQLException {
        return itemDao.getAllItems();
    }
}
