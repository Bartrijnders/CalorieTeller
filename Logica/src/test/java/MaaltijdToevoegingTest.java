import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaaltijdToevoegingTest {

    private Item testItem;
    private Toevoeging toevoeging;
    private ItemValidator validator = new ItemValidator100g();

    @BeforeEach
    void setUp() {
        testItem = new FoodItem("test",250,40,33,25);
    }

    @Test
    void itShouldGetVetWaarde() {
        //Given
        //When
        toevoeging = new MaaltijdToevoeging(340, testItem);
        //Then
        assertEquals(136, toevoeging.getVetWaarde(), 0.00001);

    }

    @Test
    void itShouldGetEiwitWaarde() {
        //Given
        //When
        toevoeging = new MaaltijdToevoeging(21 , testItem);
        //Then
        assertEquals(5.25, toevoeging.getEiwitWaarde(), 0.00001);

    }

    @Test
    void itShouldGetKoolhydraatWaarde() {
        //Given
        //When
        toevoeging = new MaaltijdToevoeging(54 , testItem);
        //Then
        assertEquals(17.82, toevoeging.getKoolhydraatWaarde(), 0.00001);

    }

    @Test
    void itShouldGetCalorieWaarde() {
        //Given
        //When
        toevoeging = new MaaltijdToevoeging(77 , testItem);
        //Then
        assertEquals(192.5, toevoeging.getCalorieWaarde(), 0.00001);

    }
}