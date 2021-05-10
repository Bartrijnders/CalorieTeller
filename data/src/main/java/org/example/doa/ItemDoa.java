package org.example.doa;

import org.example.models.Item;

import java.util.List;
import java.util.UUID;

public interface ItemDoa {
    List<Item> getAllItems();
    void deleteItem(Item item);
    void storeItem(Item item);
    Item getItemByID(UUID id);
}
