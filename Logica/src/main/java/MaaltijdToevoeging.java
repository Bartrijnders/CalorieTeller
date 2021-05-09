public class MaaltijdToevoeging implements Toevoeging {
    private double hoeveelheidInGram;
    private Item item;

    public MaaltijdToevoeging(double hoeveelheidInGram, Item item) {
        this.hoeveelheidInGram = hoeveelheidInGram;
        this.item = item;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public double getHoeveelheidInGram() {
        return hoeveelheidInGram;
    }

    @Override
    public double getVetWaarde() {
        return item.getVetWaarde() / 100 * hoeveelheidInGram;
    }

    @Override
    public double getEiwitWaarde() {
        return item.getEiwitWaarde() / 100 * hoeveelheidInGram;
    }

    @Override
    public double getKoolhydraatWaarde() {
        return item.getKoolhydraatWaarde() / 100 * hoeveelheidInGram;
    }

    @Override
    public double getCalorieWaarde() {
        return item.getCalorieWaarde() / 100 * hoeveelheidInGram;
    }

    @Override
    public void setHoeveelheidInGram(double hoeveelheidInGram) {
        this.hoeveelheidInGram = hoeveelheidInGram;
    }

    @Override
    public String toString() {
        return item.toString() + " - " + hoeveelheidInGram + " gram";
    }
}
