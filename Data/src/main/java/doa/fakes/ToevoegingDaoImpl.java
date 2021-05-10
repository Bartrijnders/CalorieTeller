package doa.fakes;

import doa.ToevoegingDao;
import models.Maaltijd;
import models.Toevoeging;

import java.util.List;
import java.util.UUID;

public class ToevoegingDaoImpl implements ToevoegingDao {

    @Override
    public List<Toevoeging> getAllToevoegingen(Maaltijd maaltijd) {
        return maaltijd.getToevoegingen();
    }

    @Override
    public void storeToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) {
        maaltijd.getToevoegingen().add(toevoeging);
    }

    @Override
    public void deleteToevoeging(Toevoeging toevoeging, Maaltijd maaltijd) {
        maaltijd.getToevoegingen().remove(toevoeging);
    }

    @Override
    public Toevoeging getByID(UUID id, Maaltijd maaltijd) {
        return maaltijd.getToevoegingen().stream().filter(x -> x.getID() == id).findFirst().orElse(null);
    }
}
