package org.bart.DTO;

import org.example.models.Toevoeging;

import java.util.UUID;

public class MaaltijdToevoegingDTO implements ToevoegingDTO {

    private Toevoeging toevoeging;

    public MaaltijdToevoegingDTO(Toevoeging toevoeging) {
        this.toevoeging = toevoeging;
    }

    @Override
    public ItemDTO getItem(){
        return new FoodItemDTO(toevoeging.getItem());
    }

    @Override
    public UUID getID() {
        return toevoeging.getID();
    }

    @Override
    public double getHoeveelheidInGram(){
        return toevoeging.getHoeveelheidInGram();
    }

    @Override
    public double getCalorieWaarde(){
        return toevoeging.getCalorieWaarde();
    }

    @Override
    public double getKoolhydraatWaarde(){
        return toevoeging.getKoolhydraatWaarde();
    }

    @Override
    public double getEiwitWaarde(){
        return toevoeging.getEiwitWaarde();
    }

    @Override
    public double getVetwaarde(){
        return toevoeging.getVetWaarde();
    }

    @Override
    public String toString() {
        return toevoeging.toString();
    }
}
