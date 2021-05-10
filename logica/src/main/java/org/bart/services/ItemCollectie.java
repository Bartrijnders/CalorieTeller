package org.bart.services;

import org.bart.DTO.ItemDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface ItemCollectie {
    ItemDTO foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException;
    void ItemVerwijderen(UUID id);

    List<ItemDTO> getItems();
}
