package org.bart.DTO;

import java.util.UUID;

public interface ItemDTO {
    String getNaam();

    UUID getId();

    double getCalorieWaarde();

    double getKoolhydraatWaarde();

    double getEiwitWaarde();

    double getVetWaarde();
}
