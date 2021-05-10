package doa;

import models.Dag;
import models.Item;
import models.Maaltijd;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MaaltijdDoa {
    List<Maaltijd> getAllItems();
    void deleteItem(Maaltijd maaltijd, Dag dag);
    void storeItem(Maaltijd maaltijd, Dag dag);
    List<Maaltijd> getMaaltijdenPerDag(LocalDate date);
}
