package org.bart.services;

import main.java.org.example.models.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ItemCollectie {
    Item foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException, SQLException;
    void ItemVerwijderen(Item item) throws SQLException;

    List<Item> getItems() throws SQLException;
}
