import models.Item;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ItemCollectie {
    Item foodItemAanmaken(@NotNull String naam, double calorieWaarde, double eiwitWaarde, double koolhydraatWaarde, double vetWaarde) throws IllegalArgumentException;

    List<Item> getItems();
}
