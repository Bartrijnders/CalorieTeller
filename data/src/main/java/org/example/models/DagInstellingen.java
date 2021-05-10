package org.example.models;

import java.util.List;

public interface DagInstellingen {

    double getVetDoel();

    void setVetDoel(double vetDoel);

    double getCalorieDoel();

    void setCalorieDoel(double calorieDoel);

    double getKoolhydraatDoel();

    void setKoolhydraatDoel(double koolhydraatDoel);

    double getEiwitDoel();


    void setEiwitDoel(double eiwitDoel);

    void addMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel);

    List<Maaltijd> getMaaltijden();
}
