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
        System.out.println("Type 0 voor: models.Maaltijd Toevoegen, Typ 1 voor: models.Maaltijd Verwijderen, Type 2 voor: models.Maaltijd Selecteren, Type 3 voor: Nieuw Product toevoegen, Type 4 voor: Product Verwijderen");
        int i = keuzeSelector(33);
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

    private Toevoeging toevoegingSelector(Maaltijd maaltijd) {
        printToevoegingen(maaltijd);
        System.out.println("kies een maaltijd door het nummer voor de maaltijd in te typen.");
        int keuze = keuzeSelector(maaltijd.getToevoegingen().size() - 1);
        return maaltijd.getToevoegingen().get(keuze);
    }

    private void printToevoegingen(Maaltijd maaltijd) {
        int i = 0;
        for (Toevoeging toevoeging : maaltijd.getToevoegingen()) {
            System.out.println(i + ": " + toevoeging.toString());
            i++;
        }
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
        System.out.println(("models.Maaltijd aanmaken!").toUpperCase());
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
        System.out.println("Nieuwe models.Maaltijd toegevoegd: " + maaltijd.toString());
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
            case 3:
                nieuwItemToevoegen();
            case 4:
                itemVerwijderen();
            default:
                dagMenu();
        }
    }

    private void nieuwItemToevoegen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("NIEUW PRODUCT TOEVOEGEN.");
        System.out.println("Voer een naam voor het nieuwe Product in.");
        scanner.nextLine();
        String naam = scanner.nextLine();
        System.out.println("Hoeveel calorien bevat " + naam + " per 100 gram?");
        double calorie = scanner.nextDouble();
        System.out.println("Hoeveel koolhydraten bevat " + naam + " per 100 gram?");
        double koolhydraat = scanner.nextDouble();
        System.out.println("Hoeveel eiwit bevat " + naam + " per 100 gram?");
        double eiwit = scanner.nextDouble();
        System.out.println("Hoeveel vet bevat " + naam + " per 100 gram?");
        double vet = scanner.nextDouble();
        Item item = itemCollectie.foodItemAanmaken(naam, calorie, eiwit, koolhydraat, vet);
        System.out.println("Nieuw product is toegevoegd!");
        System.out.println(item.toString());
        dagMenu();
    }

    private void itemVerwijderen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("PRODUCT VERWIJDEREN.");
        System.out.println("kies een product uit de lijst hieronder om te verwijderen. typ het nummer van het product in.");
        int i = 0;
        for (Item item : itemCollectie.getItems()) {
            System.out.println(i + ": " + item.toString());
            i++;
        }
        int keuze = keuzeSelector(itemCollectie.getItems().size() - 1);
        Item item = itemCollectie.getItems().remove(keuze);
        System.out.println("models.Item: " + item.getNaam() + ", is verwijdert.");
    }

    private void maaltijdKeuzeMenu(int keuze, Maaltijd maaltijd) {
        switch (keuze) {
            case 0:
                maaltijdItemToevoegen(maaltijd);
            case 1:
                maaltijdItemVerwijderen(maaltijd);
            case 2:
                dagMenu();
            default:
                maaltijdKeuzeMenu(maaltijd);
        }
    }

    private void maaltijdItemToevoegen(Maaltijd maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("PRODUCT TOEVOEGEN");
        System.out.println("Kies een product uit de onderstaande lijst door het nummer van het product in te voeren.");
        int i = 0;
        for (Item item : itemCollectie.getItems()) {
            System.out.println(i + ": " + item.toString());
            i++;
        }
        int keuze = keuzeSelector(itemCollectie.getItems().size() - 1);
        Item itemKeuze = itemCollectie.getItems().get(keuze);
        System.out.println("U heeft gekozen voor: " + itemKeuze);
        System.out.println("Vul hieronder de hoeveelheid die u wilt toevoegen van dit product in gram.");
        double hoeveelheid = scanner.nextDouble();
        maaltijd.addFoodItem(itemKeuze, hoeveelheid);
        System.out.println("models.Item Toegevoegd!");
        maaltijdKeuzeMenu(maaltijd);
    }

    private void maaltijdBekijken() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("models.Maaltijd Bekijken!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt Bekijken: ");
        Maaltijd maaltijd = maaltijdSelector();
        maaltijdKeuzeMenu(maaltijd);
        dagMenu();
    }

    private void maaltijdKeuzeMenu(Maaltijd maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("models.Maaltijd: " + maaltijd.getNaam());
        System.out.println("Calorie: " + maaltijd.getGebruikteCalorieen() + "/" + maaltijd.getCalorieDoel());
        System.out.println("Koolhydraat: " + maaltijd.getGebruikteKoolhydraten() + "/" + maaltijd.getKoolhydraatDoel());
        System.out.println("Eiwit: " + maaltijd.getGebruikteEiwitten() + "/" + maaltijd.getEiwitDoel());
        System.out.println("Vet: " + maaltijd.getGebruikteVetten() + "/" + maaltijd.getVetDoel());
        System.out.println("Items in deze maaltijd: ");
        if (maaltijd.getToevoegingen().isEmpty()) {
            System.out.println("Deze maaltijd bevat nog geen items.");
        } else {
            for (Toevoeging toevoeging : maaltijd.getToevoegingen()) {
                System.out.println(toevoeging.toString());
            }
        }
        System.out.println("Type 0 voor: models.Item Toevoegen, Typ 1 voor: models.Item Verwijderen, typ 2 voor: Terug");
        int i = keuzeSelector(2);
        maaltijdKeuzeMenu(i, maaltijd);

    }

    private void maaltijdItemVerwijderen(Maaltijd maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("models.Item verwijderen!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt verwijderen: ");
        Toevoeging toevoeging = toevoegingSelector(maaltijd);
        maaltijd.verwijderItem(toevoeging);
        System.out.println("models.Item verwijdert: " + toevoeging.toString());
        maaltijdKeuzeMenu(maaltijd);
    }

    private void maaltijdVerwijderen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("models.Maaltijd verwijderen!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt verwijderen: ");
        Maaltijd maaltijd = maaltijdSelector();
        vandaag.verwijderMaaltijd(maaltijd);
        System.out.println("models.Maaltijd verwijdert: " + maaltijd.toString());
        dagMenu();

    }
}
