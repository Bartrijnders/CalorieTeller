package org.bart.DTO;

import java.util.List;
import java.util.UUID;

public interface MaaltijdDTO {
    List<ToevoegingDTO> getToevoegingen();

    UUID getId();

    String getNaam();

    double getCalorieDoel();

    double getKoolhydraatDoel();

    double getEiwitDoel();

    double getVetDoel();

    double getGebruikteCalorieen();

    double getGebruikteKoolhydraat();

    double getGebruikteEiwit();

    double getGebruikteVet();

    @Override
    String toString();
}
