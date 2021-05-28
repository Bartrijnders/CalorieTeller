package org.example.models;

import java.util.List;
import java.util.UUID;

public interface DagInstellingen {

    UUID getId();

    double getVetDoel();

    void setVetDoel(double vetDoel);

    double getCalorieDoel();

    void setCalorieDoel(double calorieDoel);

    double getKoolhydraatDoel();

    void setKoolhydraatDoel(double koolhydraatDoel);

    double getEiwitDoel();

    void setMaaltijden(List<Maaltijd> maaltijden);

    void setEiwitDoel(double eiwitDoel);

    void addMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel);

    List<Maaltijd> getMaaltijden();
}
