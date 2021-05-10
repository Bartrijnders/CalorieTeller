package org.example.models;

public interface Toevoeging extends IDAble {
    Item getItem();

    void setItem(Item item);

    double getHoeveelheidInGram();

    double getVetWaarde();

    double getEiwitWaarde();

    double getKoolhydraatWaarde();

    double getCalorieWaarde();

    void setHoeveelheidInGram(double hoeveelheidInGram);
}
