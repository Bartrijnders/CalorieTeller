package org.example.doa;

import org.example.models.Dag;
import org.example.models.Maaltijd;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MaaltijdDoa {
    List<Maaltijd> getAllItems();
    void deleteItem(Maaltijd maaltijd, Dag dag);
    void storeItem(Maaltijd maaltijd, Dag dag);
    List<Maaltijd> getMaaltijdenPerDag(LocalDate date);
    Maaltijd getMaaltijd(UUID id, LocalDate date);
}
