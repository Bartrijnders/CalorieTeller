import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StandaardDag {
    private DateProvider dateProvider;
    private List<Maaltijd> maaltijden;
    private LocalDate datum;
    private double totaalCalorieDoel;
    private double totaalKoolhydraatDoel;
    private double totaalEiwitDoel;
    private double totaalVetDoel;

    public StandaardDag(DateProvider dateProvider, List<Maaltijd> maaltijden, LocalDate datum, double totaalCalorieDoel, double totaalKoolhydraatDoel, double totaalEiwitDoel, double totaalVetDoel) {
        this.dateProvider = dateProvider;
        this.maaltijden = maaltijden;
        this.datum = datum;
        this.totaalCalorieDoel = totaalCalorieDoel;
        this.totaalKoolhydraatDoel = totaalKoolhydraatDoel;
        this.totaalEiwitDoel = totaalEiwitDoel;
        this.totaalVetDoel = totaalVetDoel;
    }

    public StandaardDag(DateProvider dateProvider, DagInstellingen dagInstellingen) {
        this.dateProvider = dateProvider;
        this.maaltijden = dagInstellingen.getMaaltijden();
        this.datum = dateProvider.GetCurrentDate();
        this.totaalCalorieDoel = dagInstellingen.getCalorieDoel();
        this.totaalKoolhydraatDoel = dagInstellingen.getKoolhydraatDoel();
        this.totaalEiwitDoel = dagInstellingen.getEiwitDoel();
        this.totaalVetDoel = dagInstellingen.getVetDoel();
    }

    public void addMaaltijd(double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel){
        Maaltijd maaltijd = new StandaardMaaltijd(calorieDoel, koolhydraatDoel, eiwitDoel, vetDoel);
        this.maaltijden.add(maaltijd);
    }

    public void verwijderMaaltijd(Maaltijd maaltijd){
        this.maaltijden.remove(maaltijd);
    }

    public double getTotaalCalorieDoel() {
        return totaalCalorieDoel;
    }

    public void setTotaalCalorieDoel(double totaalCalorieDoel) {
        this.totaalCalorieDoel = totaalCalorieDoel;
    }

    public double getTotaalKoolhydraatDoel() {
        return totaalKoolhydraatDoel;
    }

    public void setTotaalKoolhydraatDoel(double totaalKoolhydraatDoel) {
        this.totaalKoolhydraatDoel = totaalKoolhydraatDoel;
    }

    public double getTotaalEiwitDoel() {
        return totaalEiwitDoel;
    }

    public void setTotaalEiwitDoel(double totaalEiwitDoel) {
        this.totaalEiwitDoel = totaalEiwitDoel;
    }

    public double getTotaalVetDoel() {
        return totaalVetDoel;
    }

    public void setTotaalVetDoel(double totaalVetDoel) {
        this.totaalVetDoel = totaalVetDoel;
    }

    public List<Maaltijd> getMaaltijden() {
        return maaltijden;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
