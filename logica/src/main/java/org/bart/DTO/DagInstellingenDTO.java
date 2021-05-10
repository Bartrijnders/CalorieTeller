package org.bart.DTO;

import java.util.List;

public interface DagInstellingenDTO {
    Double getCalorieDoel();

    Double getKoolhydraatDoel();

    Double getEiwitDoel();

    Double getVetdoel();

    List<MaaltijdDTO> getMaaltijden();
}
