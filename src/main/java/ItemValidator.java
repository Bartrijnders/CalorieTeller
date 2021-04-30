public interface ItemValidator {
    boolean valideerWaardes(String naam, double calorieWaarde ,double vetWaarde, double koolhydraatWaarde, double eiwitWaarde) throws IllegalArgumentException;
}
