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

    public StandaardDag(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        this.maaltijden = new ArrayList<>();
        this.datum = dateProvider.GetCurrentDate();
        this.totaalCalorieDoel = 0;
        this.totaalKoolhydraatDoel = 0;
        this.totaalEiwitDoel = 0;
        this.totaalVetDoel = 0;
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
