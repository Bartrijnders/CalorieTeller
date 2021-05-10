import doa.DagDao;
import doa.MaaltijdDoa;
import models.Dag;

import java.util.List;

public class StandaardDagCollectie {

    private List<Dag> dagen;
    private DagDao dagDao;
    private MaaltijdDoa maaltijdDoa;

    public StandaardDagCollectie(DagDao dagDao, MaaltijdDoa maaltijdDoa) {
        this.dagDao = dagDao;
        this.dagen = dagDao.getAllDagen();
        this.maaltijdDoa = maaltijdDoa;
    }

    public Dag getToday(){
        return dagDao.getToday();
    }

}
