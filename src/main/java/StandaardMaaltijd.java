import java.util.ArrayList;
import java.util.List;

public class StandaardMaaltijd implements Maaltijd {

    private final List<Toevoeging> toevoegingen;
    private String naam;
    private  double calorieDoel;
    private double koolhydraatDoel;
    private double eiwitDoel;
    private double vetDoel;

    public StandaardMaaltijd(List<Toevoeging> toevoegingen, String naam,  double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        this.toevoegingen = toevoegingen;
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.vetDoel = vetDoel;
    }

    public StandaardMaaltijd(String naam, double calorieDoel, double koolhydraatDoel, double eiwitDoel, double vetDoel) {
        toevoegingen = new ArrayList<>();
        this.calorieDoel = calorieDoel;
        this.koolhydraatDoel = koolhydraatDoel;
        this.eiwitDoel = eiwitDoel;
        this.vetDoel = vetDoel;
    }

    @Override
    public List<Toevoeging> getToevoegingen() {
        return toevoegingen;
    }

    @Override
    public double getCalorieDoel() {
        return calorieDoel;
    }

    @Override
    public void setCalorieDoel(double calorieDoel) {
        this.calorieDoel = calorieDoel;
    }

    @Override
    public double getKoolhydraatDoel() {
        return koolhydraatDoel;
    }

    @Override
    public void setKoolhydraatDoel(double koolhydraatDoel) {
        this.koolhydraatDoel = koolhydraatDoel;
    }

    @Override
    public double getEiwitDoel() {
        return eiwitDoel;
    }

    @Override
    public void setEiwitDoel(double eiwitDoel) {
        this.eiwitDoel = eiwitDoel;
    }

    @Override
    public double getVetDoel() {
        return vetDoel;
    }

    @Override
    public void setVetDoel(double vetDoel) {
        this.vetDoel = vetDoel;
    }

    // misschien is het slim om een interface hievoor aan te maken.
    @Override
    public void addFoodItem(Item item, double hoeveelheidInGram){
        Toevoeging toevoeging = new MaaltijdToevoeging(hoeveelheidInGram, item);
        this.toevoegingen.add(toevoeging);
    }

    @Override
    public void verwijderItem(MaaltijdToevoeging toevoeging) {
        this.toevoegingen.remove(toevoeging);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
