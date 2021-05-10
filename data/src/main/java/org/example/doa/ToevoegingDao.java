package org.example.doa;

import org.example.models.Maaltijd;
import org.example.models.Toevoeging;

import java.util.List;
import java.util.UUID;

public interface ToevoegingDao {
    List<Toevoeging> getAllToevoegingen(Maaltijd maaltijd);
    void storeToevoeging(Toevoeging toevoeging, Maaltijd maaltijd);
    void deleteToevoeging(Toevoeging toevoeging, Maaltijd maaltijd);
    Toevoeging getByID(UUID id, Maaltijd maaltijd);
}
