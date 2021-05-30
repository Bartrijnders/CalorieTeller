package org.example.doa;

import main.java.org.example.models.Maaltijd;
import main.java.org.example.models.Toevoeging;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ToevoegingDao {
    List<Toevoeging> getAllToevoegingen(Maaltijd maaltijd) throws SQLException;
    void storeToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) throws SQLException;
    void deleteToevoeging(Toevoeging toevoeging, Maaltijd maaltijd);
    Toevoeging getByID(UUID id, Maaltijd maaltijd) throws SQLException;
}
