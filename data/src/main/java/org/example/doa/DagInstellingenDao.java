package org.example.doa;

import org.example.models.DagInstellingen;

import java.sql.SQLException;

public interface DagInstellingenDao {
    DagInstellingen getInstellingen() throws SQLException;
}
