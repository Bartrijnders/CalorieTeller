package DTO;

import models.Maaltijd;

import java.time.LocalDate;
import java.util.List;

public interface DagDTO {
    List<Maaltijd> getAllMaaltijden();

    double getCalorieDoel();

    double getKoolhydraatDoel();

    double getEiwitDoel();

    double getVetDoel();

    LocalDate getDatum();
}
