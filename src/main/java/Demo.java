import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    private DagInstellingen dagInstellingen;
    private List<Dag> dagen;
    private Dag vandaag;
    private DateProvider dateProvider;
    private ItemCollectie itemCollectie;
    private ItemValidator itemValidator;

    public Demo() {
        this.dagInstellingen = new StandaardDagInstellingen();
        this.dagen = new ArrayList<>();
        this.dateProvider = new SystemDateProvider(Clock.systemDefaultZone());
        this.vandaag = new StandaardDag(dateProvider, dagInstellingen);
        dagen.add(vandaag);
        itemValidator = new ItemValidator100g();
        itemCollectie = new FoodItemCollectie(itemValidator);
        itemCollectie.foodItemAanmaken("Banaan", 92, 1, 20, 0.3);
        itemCollectie.foodItemAanmaken("Hardgekookt Ei", 128, 12.4, 0, 8.8);
        itemCollectie.foodItemAanmaken("Havermout", 370, 14, 59, 7);
        itemCollectie.foodItemAanmaken("Magere Kwark", 180, 10.6, 5.8, 12.3);
        itemCollectie.foodItemAanmaken("Kokos Geraspt", 655, 7.5, 22.1, 65.4);
    }

    public void start(){

    }
}
