

public class FoodItem implements Item {
    private String naam;
    private double calorieWaarde;
    private double vetWaarde;
    private double koolhydraatWaarde;
    private double eiwitWaarde;

    // naam. calorie waarde per 100g. vet waarde per 100g, koolhydraatWaarde per 100g, eiwit waarde per 100g.
    public FoodItem(String naam, double calorieWaarde, double vetWaarde, double koolhydraatWaarde, double eiwitWaarde) {
            this.naam = naam;
            this.calorieWaarde = calorieWaarde;
            this.vetWaarde = vetWaarde;
            this.koolhydraatWaarde = koolhydraatWaarde;
            this.eiwitWaarde = eiwitWaarde;
    }

    //returned de naam van een item.
    @Override
    public String getNaam() {
        return naam;
    }

    // returned de calorie waarde van een item.
    @Override
    public double getCalorieWaarde() {
        return calorieWaarde;
    }
    // returned de vet waarde van een item.
    @Override
    public double getVetWaarde() {
        return vetWaarde;
    }

    //returned de koolhydraat waarde van een item.
    @Override
    public double getKoolhydraatWaarde() {
        return koolhydraatWaarde;
    }
    // returned de eiwit waarde van een item.
    @Override
    public double getEiwitWaarde() {
        return eiwitWaarde;
    }





    @Override
    public String toString() {
        return "Item: " +
                "naam='" + naam + '\'' +
                ", calorieWaarde=" + calorieWaarde +
                ", vetWaarde=" + vetWaarde +
                ", koolhydraatWaarde=" + koolhydraatWaarde +
                ", eiwitWaarde=" + eiwitWaarde;
    }
}
