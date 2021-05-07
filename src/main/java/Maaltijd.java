import java.util.List;

public interface Maaltijd extends foodItemHolder {
    List<Toevoeging> getToevoegingen();

    double getCalorieDoel();

    void setCalorieDoel(double calorieDoel);

    double getKoolhydraatDoel();

    void setKoolhydraatDoel(double koolhydraatDoel);

    double getEiwitDoel();

    void setEiwitDoel(double eiwitDoel);

    double getVetDoel();

    void setVetDoel(double vetDoel);

    void verwijderItem(MaaltijdToevoeging toevoeging);

}
