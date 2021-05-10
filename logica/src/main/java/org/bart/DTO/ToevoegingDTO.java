package org.bart.DTO;

import java.util.UUID;

public interface ToevoegingDTO {
    ItemDTO getItem();

    UUID getID();

    double getHoeveelheidInGram();

    double getCalorieWaarde();

    double getKoolhydraatWaarde();

    double getEiwitWaarde();

    double getVetwaarde();

    @Override
    String toString();
}
