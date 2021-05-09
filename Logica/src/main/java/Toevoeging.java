public interface Toevoeging {
    Item getItem();

    void setItem(Item item);

    double getHoeveelheidInGram();

    double getVetWaarde();

    double getEiwitWaarde();

    double getKoolhydraatWaarde();

    double getCalorieWaarde();

    void setHoeveelheidInGram(double hoeveelheidInGram);
}
