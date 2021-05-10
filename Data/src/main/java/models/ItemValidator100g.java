package models;

public class ItemValidator100g implements ItemValidator {

    @Override
    public boolean valideerWaardes(String naam, double calorieWaarde, double vetWaarde, double koolhydraatWaarde, double eiwitWaarde) throws IllegalArgumentException{
        double totaal = vetWaarde + koolhydraatWaarde + eiwitWaarde;
        if(totaal > 100 || totaal < 0){
            throw new IllegalArgumentException("Totaal van de voedingswaardes mag niet groter zijn dan 100g of kleiner dan 0g.");
        }else if(vetWaarde > 100 || vetWaarde < 0){
            throw new IllegalArgumentException("Vet waarde kan niet groter zijn dan 100g of kleiner dan 0g.");
        }else if(eiwitWaarde > 100 || eiwitWaarde < 0){
            throw new IllegalArgumentException("Eiwit waarde kan niet groter zijn dan 100g of kleiner dan 0g.");
        }else if(koolhydraatWaarde > 100 || koolhydraatWaarde < 0){
            throw new IllegalArgumentException("Koolhydraat waarde kan niet groter zijn dan 100g of kleiner dan 0g.");
        } else if (calorieWaarde < 0){
            throw new IllegalArgumentException("Calorie waarde kan niet kleiner dan 0 zijn.");
        }else if(naam.length() > 255 || naam.length() < 1){
            throw new IllegalArgumentException("Naam mag niet langer zijn dan 255 tekens en groter dan 0 tekens.");
        } else {
            return true;
        }
    }


}
