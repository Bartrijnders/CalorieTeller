package org.bart.services;

import org.bart.DTO.FoodItemDTO;
import org.bart.DTO.ItemDTO;
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
    private List<Item> items;
    private ItemValidator itemValidator;
    private ItemDao itemDao;

    public FoodItemCollectie(ItemDao itemDao, ItemValidator itemValidator) throws SQLException {
        this.itemDao = itemDao;
        items = itemDao.getAllItems();
        this.itemValidator = itemValidator;
    }



    @Override
    public ItemDTO foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException, SQLException {
        if(itemValidator.valideerWaardes(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde)){
            Item item = new FoodItem(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde);
            itemDao.storeItem(item);
            this.items = itemDao.getAllItems();
            return new FoodItemDTO(item);
        }
        else return null;
    }

    @Override
    public void ItemVerwijderen(UUID id) throws SQLException {
        Item item = itemDao.getItemByID(id);
        itemDao.deleteItem(item);
        this.items = itemDao.getAllItems();
    }

    @Override
    public List<ItemDTO> getItems() {
        List<ItemDTO> toReturn = new ArrayList<>();
        for(Item item : items){
            toReturn.add(new FoodItemDTO(item));
        }
        return toReturn;
    }
}
