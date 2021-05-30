package org.example.doa;

import main.java.org.example.models.Dag;
import main.java.org.example.models.Maaltijd;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MaaltijdDoa {
    List<Maaltijd> getAllItems() throws SQLException;
    void deleteItem(Maaltijd maaltijd, Dag dag) throws SQLException;
    void storeItem(Maaltijd maaltijd, Dag dag) throws SQLException;
    List<Maaltijd> getMaaltijdenPerDag(LocalDate date) throws SQLException;
    Maaltijd getMaaltijd(UUID id, LocalDate date) throws SQLException;
}
