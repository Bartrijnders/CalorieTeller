package org.bart.DTO;

import main.java.org.example.models.Dag;

import java.time.LocalDate;
import java.util.List;

public interface DagDTO {
    List<MaaltijdDTO> getAllMaaltijden();

    double getCalorieDoel();

    double getKoolhydraatDoel();

    double getEiwitDoel();

    double getVetDoel();

    LocalDate getDatum();

    Dag getDag();
}
