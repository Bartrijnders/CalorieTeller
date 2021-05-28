package org.example.doa;

import org.example.models.Dag;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DagDao {
    List<Dag> getAllDagen() throws SQLException;
    void storeDag(Dag dag) throws SQLException;
    Dag getDagByDate(LocalDate date) throws SQLException;
    Dag getToday() throws SQLException;
}
