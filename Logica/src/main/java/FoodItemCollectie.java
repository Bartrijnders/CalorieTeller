import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FoodItemCollectie implements ItemCollectie {
    private final List<Item> items;
    private ItemValidator itemValidator;

    public FoodItemCollectie(List<Item> items, ItemValidator itemValidator) {
        this.items = items;
        this.itemValidator = itemValidator;
    }

    public FoodItemCollectie(ItemValidator itemValidator) {
        this.items = new ArrayList<>();
        this.itemValidator = itemValidator;
    }

    @Override
    public Item foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException{
        if(itemValidator.valideerWaardes(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde)){
            Item item = new FoodItem(naam, calorieWaarde, vetWaarde, koolhydraatWaarde, eiwitWaarde);
            items.add(item);
            return item;
        }
        else return null;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
