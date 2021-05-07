import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Iteratie1 {
    private DagInstellingen dagInstellingen;
    private List<Dag> dagen;
    private Dag vandaag;
    private DateProvider dateProvider;
    private ItemCollectie itemCollectie;
    private ItemValidator itemValidator;
    private Scanner scanner;

    public Iteratie1() {
        this.dagInstellingen = new StandaardDagInstellingen();
        dagInstellingen.addMaaltijd("Ontbijt", 800, 200, 200, 400);
        dagInstellingen.addMaaltijd("Lunch", 800, 200, 200, 400);
        dagInstellingen.addMaaltijd("Diner", 800, 200, 200, 400);
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
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(("Welcome bij uw calorie teller!").toUpperCase(Locale.ROOT));
        System.out.println("Het is vandaag: " + vandaag.getDatum());
        dagMenu();

    }

    private void dagMenu() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("hieronder volgen uw maaltijden:");
        printMaaltijden();
        System.out.println();
        System.out.println("wat wilt u doen?");
        System.out.println("Type 0 voor: Maaltijd Toevoegen, Typ 1 voor: Maaltijd Verwijderen, Type 2 voor: Maaltijd Selecteren");
        int i = keuzeSelector(2);
        dagKeuze(i);
    }

    private int keuzeSelector(int maxInt) {
        boolean toggle = false;
        int keuze;
        do {
            keuze = scanner.nextInt();
            if (keuze < 0 || keuze > maxInt) {
                System.out.println("Kies een geldig nummer. probeer hieronder opnieuw.");
            } else {
                toggle = true;
            }
        } while (!toggle);
        return keuze;
    }

    private Maaltijd maaltijdSelector() {
        printMaaltijden();
        System.out.println("kies een maaltijd door het nummer voor de maaltijd in te typen.");
        int keuze = keuzeSelector(vandaag.getMaaltijden().size() - 1);
        return vandaag.getMaaltijden().get(keuze);
    }

    private void printMaaltijden() {
        int i = 0;
        for (Maaltijd maaltijd : vandaag.getMaaltijden()) {
            System.out.println(i + ": " + maaltijd.toString());
            i++;
        }
    }

    private void maaltijdToevoegen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Maaltijd aanmaken!").toUpperCase());
        System.out.println("Vul een naam in:");
        scanner.nextLine();
        String naam = scanner.nextLine();
        System.out.println("Vul een calorie doel in: ");
        double calorie = scanner.nextDouble();
        System.out.println("Vul een koolhydraat doel in: ");
        double koolhydraat = scanner.nextDouble();
        System.out.println("Vul een eiwit doel in: ");
        double eiwit = scanner.nextDouble();
        System.out.println("Vul een vet doel in: ");
        double vet = scanner.nextDouble();
        Maaltijd maaltijd = vandaag.addMaaltijd(naam, calorie, koolhydraat, eiwit, vet);
        System.out.println("Nieuwe Maaltijd toegevoegd: " + maaltijd.toString());
        dagMenu();
    }

    private void dagKeuze(int keuze) {
        switch (keuze) {
            case 0:
                maaltijdToevoegen();
            case 1:
                maaltijdVerwijderen();
            case 2:
                maaltijdBekijken();
        }
    }

    private void maaltijdBekijken() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Maaltijd Bekijken!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt Bekijken: ");
        Maaltijd maaltijd = maaltijdSelector();
        maaltijdMenu(maaltijd);
        dagMenu();
    }

    private void maaltijdMenu(Maaltijd maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Maaltijd: " + maaltijd.getNaam());
        System.out.println("Calorie: " + maaltijd.getCalorieDoel());
    }

    private void maaltijdVerwijderen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Maaltijd verwijderen!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt verwijderen: ");
        Maaltijd maaltijd = maaltijdSelector();
        vandaag.verwijderMaaltijd(maaltijd);
        System.out.println("Maaltijd verwijdert: " + maaltijd.toString());
        dagMenu();

    }
}
