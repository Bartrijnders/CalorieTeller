package org.example.doa.fakes;

import org.example.doa.ItemDao;
import main.java.org.example.models.FoodItem;
import main.java.org.example.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeItemDaoImpl implements ItemDao {

    private final List<Item> items;

    public FakeItemDaoImpl() {
        this.items = new ArrayList<>();
        Item item = new FoodItem("Banaan", 92, 1, 20, 0.3);
        Item item1 = new FoodItem("Hardgekookt ei", 128, 12.4, 0, 8.8);
        Item item2 = new FoodItem("HaverMout", 370, 14, 59, 7);
        Item item3 = new FoodItem("Magere Kwark", 180, 10.6, 5.8, 12.3);
        Item item4 = new FoodItem("Kokos Geraspt", 655, 7.5, 22.1, 65.4);

        items.add(item);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

    }
    @Override
    public List<Item> getAllItems() {
        return items;
    }

    @Override
    public void deleteItem(Item item) {
        items.remove(item);
    }

    @Override
    public void storeItem(Item item) {
        items.add(item);
    }

    @Override
    public Item getItemByID(UUID id) {
        return items.stream().filter(x -> x.getID() == id).findFirst().orElse(null);
    }
}
