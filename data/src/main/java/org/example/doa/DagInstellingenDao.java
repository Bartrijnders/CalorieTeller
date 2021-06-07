package org.example.doa;

import main.java.org.example.models.Dag;
import main.java.org.example.models.DagInstellingen;

import java.sql.SQLException;

public interface DagInstellingenDao {
    DagInstellingen getInstellingen() throws SQLException;
    void updateInstellingen(DagInstellingen dagInstellingen) throws SQLException;
}
