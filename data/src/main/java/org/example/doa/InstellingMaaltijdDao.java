package org.example.doa;

import org.example.models.DagInstellingen;
import org.example.models.Maaltijd;

import java.sql.SQLException;
import java.util.List;

public interface InstellingMaaltijdDao {
    List<Maaltijd> getInstellingsMaaltijden(DagInstellingen dagInstellingen) throws SQLException;
}
