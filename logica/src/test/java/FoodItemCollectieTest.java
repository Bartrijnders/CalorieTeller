import org.bart.DTO.ItemDTO;
import org.bart.services.FoodItemCollectie;
import org.bart.services.ItemCollectie;
import org.example.doa.ItemDao;
import org.example.doa.fakes.FakeItemDaoImpl;
import main.java.org.example.models.FoodItem;
import main.java.org.example.models.ItemValidator;
import main.java.org.example.models.ItemValidator100g;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
class FoodItemCollectieTest {

    @Mock
    ItemValidator itemValidator = Mockito.mock(ItemValidator.class);
    ItemDao itemDao = new FakeItemDaoImpl();

    ItemValidator test = new ItemValidator100g();

    @Spy
    @InjectMocks
    ItemCollectie itemCollectie = new FoodItemCollectie(itemDao,itemValidator);

    FoodItemCollectieTest() throws SQLException {
    }


    @Test
    void itShouldFoodItemAanmaken() throws SQLException {
        Mockito.when(itemValidator.valideerWaardes(anyString(), anyDouble(),  anyDouble(),  anyDouble(), anyDouble())).thenReturn(true);
        ItemDTO result = itemCollectie.foodItemAanmaken("test", 100, 10, 20 ,30);
        //Then
        assertTrue(result instanceof FoodItem);

    }


}