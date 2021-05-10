package org.example.models;

public interface Item extends IDAble {
    //returned de naam van een item.
    String getNaam();

    // returned de calorie waarde van een item.
    double getCalorieWaarde();

    // returned de vet waarde van een item.
    double getVetWaarde();

    //returned de koolhydraat waarde van een item.
    double getKoolhydraatWaarde();

    // returned de eiwit waarde van een item.
    double getEiwitWaarde();

    @Override
    String toString();
}
