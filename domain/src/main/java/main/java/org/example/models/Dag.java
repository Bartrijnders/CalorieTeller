package main.java.org.example.models;

import java.time.LocalDate;
import java.util.List;

public interface Dag {
    Maaltijd addMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel);

    void verwijderMaaltijd(Maaltijd maaltijd);

    double getTotaalCalorieDoel();

    void setTotaalCalorieDoel(double totaalCalorieDoel);

    double getTotaalKoolhydraatDoel();

    void setTotaalKoolhydraatDoel(double totaalKoolhydraatDoel);

    double getTotaalEiwitDoel();

    void setTotaalEiwitDoel(double totaalEiwitDoel);

    double getTotaalVetDoel();

    void setTotaalVetDoel(double totaalVetDoel);

    List<Maaltijd> getMaaltijden();

    LocalDate getDatum();

    double getUsedKoolhydraten();

    double getUsedCalorieen();

    double getUsedVetten();

    double getUsedEiwitten();
}
