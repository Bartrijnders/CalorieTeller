import doa.MaaltijdDoa;
import models.Dag;
import models.Maaltijd;
import models.StandaardMaaltijd;

import java.util.List;

public class MaaltijdService {

    private final MaaltijdDoa maaltijdDoa;

    public MaaltijdService(MaaltijdDoa maaltijdDoa) {
        this.maaltijdDoa = maaltijdDoa;
    }

    public List<Maaltijd> maaltijdenVanDagOphalen(Dag dag){
        return maaltijdDoa.getMaaltijdenPerDag(dag.getDatum());
    }
}
