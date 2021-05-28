package org.example.demo;

import org.bart.DTO.DagDTO;
import org.bart.DTO.ItemDTO;
import org.bart.DTO.MaaltijdDTO;
import org.bart.DTO.ToevoegingDTO;
import org.bart.services.ServiceContainter;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Iteratie1 {
    private final ServiceContainter container;
    private final Scanner scanner;
    private final DagDTO dagDTO;

    public Iteratie1() {
        scanner = new Scanner(System.in);
        container =  new ServiceContainter();
        dagDTO = container.getStandaardDagCollectie().getToday();
    }

    public void start() {
        System.out.println(("Welcome bij uw calorie teller!").toUpperCase(Locale.ROOT));
        System.out.println("Het is vandaag: " + container.getStandaardDagCollectie().getToday());
        dagMenu();

    }

    private void dagMenu() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("hieronder volgen uw maaltijden:");
        printMaaltijden(dagDTO);
        System.out.println();
        System.out.println("wat wilt u doen?");
        System.out.println("Type 0 voor: Maaltijd Toevoegen, Typ 1 voor: Maaltijd Verwijderen, Type 2 voor: Maaltijd Selecteren, Type 3 voor: Nieuw Product toevoegen, Type 4 voor: Product Verwijderen");
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

    private MaaltijdDTO maaltijdSelector() {
        printMaaltijden(dagDTO);
        System.out.println("kies een maaltijd door het nummer voor de maaltijd in te typen.");
        int keuze = keuzeSelector(container.getStandaardDagCollectie().getToday().getAllMaaltijden().size());
        return container.getStandaardDagCollectie().getToday().getAllMaaltijden().get(keuze);
    }

    private ToevoegingDTO toevoegingSelector(MaaltijdDTO maaltijd) {
        printToevoegingen(maaltijd);
        System.out.println("kies een maaltijd door het nummer voor de maaltijd in te typen.");
        int keuze = keuzeSelector(maaltijd.getToevoegingen().size() - 1);
        return maaltijd.getToevoegingen().get(keuze);
    }

    private void printToevoegingen(MaaltijdDTO maaltijd) {
        int i = 0;
        for (ToevoegingDTO toevoeging : maaltijd.getToevoegingen()) {
            System.out.println(i + ": " + toevoeging.toString());
            i++;
        }
    }

    private void printMaaltijden(DagDTO dagDTO) {
        int i = 0;
        for (MaaltijdDTO maaltijd : dagDTO.getAllMaaltijden()) {
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
        MaaltijdDTO maaltijd = null;
        try {
            maaltijd = container.getStandaardDagCollectie().maaltijdAanDagToevoegen(dagDTO.getDatum(),naam, calorie, koolhydraat, eiwit, vet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        ItemDTO item = container.getFoodItemCollectie().foodItemAanmaken(naam, calorie,eiwit,koolhydraat, vet);
        System.out.println("Nieuw product is toegevoegd!");
        System.out.println(item.toString());
        dagMenu();
    }

    private void itemVerwijderen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("PRODUCT VERWIJDEREN.");
        System.out.println("kies een product uit de lijst hieronder om te verwijderen. typ het nummer van het product in.");
        int i = 0;
        for (ItemDTO item : container.getFoodItemCollectie().getItems()) {
            System.out.println(i + ": " + item.toString());
            i++;
        }
        int keuze = keuzeSelector(container.getFoodItemCollectie().getItems().size() - 1);
        ItemDTO item = container.getFoodItemCollectie().getItems().remove(keuze);
        System.out.println("Item: " + item.getNaam() + ", is verwijdert.");
    }

    private void maaltijdKeuzeMenu(int keuze, MaaltijdDTO maaltijd) {
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

    private void maaltijdItemToevoegen(MaaltijdDTO maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("PRODUCT TOEVOEGEN");
        System.out.println("Kies een product uit de onderstaande lijst door het nummer van het product in te voeren.");
        int i = 0;
        for (ItemDTO item : container.getFoodItemCollectie().getItems()) {
            System.out.println(i + ": " + item.toString());
            i++;
        }
        int keuze = keuzeSelector(container.getFoodItemCollectie().getItems().size() - 1);
        ItemDTO itemKeuze = container.getFoodItemCollectie().getItems().get(keuze);
        System.out.println("U heeft gekozen voor: " + itemKeuze);
        System.out.println("Vul hieronder de hoeveelheid die u wilt toevoegen van dit product in gram.");
        double hoeveelheid = scanner.nextDouble();
        container.getMaaltijdService().addItem(maaltijd.getId(),itemKeuze.getId(),dagDTO.getDatum(),hoeveelheid);
        System.out.println("Item Toegevoegd!");
        maaltijdKeuzeMenu(maaltijd);
    }

    private void maaltijdBekijken() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Maaltijd Bekijken!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt Bekijken: ");
        MaaltijdDTO maaltijd = maaltijdSelector();
        maaltijdKeuzeMenu(maaltijd);
        dagMenu();
    }

    private void maaltijdKeuzeMenu(MaaltijdDTO maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Maaltijd: " + maaltijd.getNaam());
        System.out.println("Calorie: " + maaltijd.getGebruikteCalorieen() + "/" + maaltijd.getCalorieDoel());
        System.out.println("Koolhydraat: " + maaltijd.getGebruikteKoolhydraat() + "/" + maaltijd.getKoolhydraatDoel());
        System.out.println("Eiwit: " + maaltijd.getGebruikteEiwit() + "/" + maaltijd.getEiwitDoel());
        System.out.println("Vet: " + maaltijd.getGebruikteEiwit() + "/" + maaltijd.getVetDoel());
        System.out.println("Items in deze maaltijd: ");
        if (maaltijd.getToevoegingen().isEmpty()) {
            System.out.println("Deze maaltijd bevat nog geen items.");
        } else {
            for (ToevoegingDTO toevoeging : maaltijd.getToevoegingen()) {
                System.out.println(toevoeging.toString());
            }
        }
        System.out.println("Type 0 voor: Item Toevoegen, Typ 1 voor: Item Verwijderen, typ 2 voor: Terug");
        int i = keuzeSelector(2);
        maaltijdKeuzeMenu(i, maaltijd);

    }

    private void maaltijdItemVerwijderen(MaaltijdDTO maaltijd) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Item verwijderen!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt verwijderen: ");
        ToevoegingDTO toevoeging = toevoegingSelector(maaltijd);
        container.getMaaltijdService().removeItem(maaltijd.getId(),toevoeging.getID(),dagDTO.getDatum());
        System.out.println("Item verwijdert: " + toevoeging.toString());
        maaltijdKeuzeMenu(maaltijd);
    }

    private void maaltijdVerwijderen() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println(("Maaltijd verwijderen!").toUpperCase());
        System.out.println("selecteer een maaltijd die u wilt verwijderen: ");
        MaaltijdDTO maaltijd = maaltijdSelector();
        container.getStandaardDagCollectie().maaltijdVerwijderen(dagDTO.getDatum(),maaltijd.getId());
        System.out.println("Maaltijd verwijdert: " + maaltijd.toString());
        dagMenu();

    }
}
