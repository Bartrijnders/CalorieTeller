package DTO;

import models.Toevoeging;

import java.util.List;

public interface MaaltijdDTO {
    List<Toevoeging> getToevoegingen();

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
