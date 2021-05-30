package org.example.doa;

import main.java.org.example.models.DagInstellingen;
import main.java.org.example.models.Maaltijd;

import java.sql.SQLException;
import java.util.List;

public interface InstellingMaaltijdDao {
    List<Maaltijd> getInstellingsMaaltijden(DagInstellingen dagInstellingen) throws SQLException;
}
