package org.example.doa;

import main.java.org.example.models.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ItemDao {
    List<Item> getAllItems() throws SQLException;
    void deleteItem(Item item) throws SQLException;
    void storeItem(Item item) throws SQLException;
    Item getItemByID(UUID id) throws SQLException;
}
