package org.bart.DTO;

import org.example.models.Item;

import java.util.UUID;

public class FoodItemDTO implements ItemDTO {

    private Item item;

    public FoodItemDTO(Item item) {
        this.item = item;
    }

    @Override
    public String getNaam() {
        return item.getNaam();
    }

    @Override
    public UUID getId() {
        return item.getID();
    }

    @Override
    public double getCalorieWaarde(){
        return item.getCalorieWaarde();
    }

    @Override
    public double getKoolhydraatWaarde(){
        return item.getKoolhydraatWaarde();
    }

    @Override
    public double getEiwitWaarde(){
        return item.getEiwitWaarde();
    }

    @Override
    public double getVetWaarde(){
        return item.getVetWaarde();
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
