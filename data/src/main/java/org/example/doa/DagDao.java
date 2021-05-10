package org.example.doa;

import org.example.models.Dag;

import java.time.LocalDate;
import java.util.List;

public interface DagDao {
    List<Dag> getAllDagen();
    void storeDag(Dag dag);
    Dag getDagByDate(LocalDate date);
    Dag getToday();
}