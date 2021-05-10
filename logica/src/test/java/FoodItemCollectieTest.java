import org.bart.DTO.ItemDTO;
import org.bart.services.FoodItemCollectie;
import org.bart.services.ItemCollectie;
import org.example.doa.ItemDoa;
import org.example.doa.fakes.FakeItemDoaImpl;
import org.example.models.FoodItem;
import org.example.models.ItemValidator;
import org.example.models.ItemValidator100g;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
class FoodItemCollectieTest {

    @Mock
    ItemValidator itemValidator = Mockito.mock(ItemValidator.class);
    ItemDoa itemDoa = new FakeItemDoaImpl();

    ItemValidator test = new ItemValidator100g();

    @Spy
    @InjectMocks
    ItemCollectie itemCollectie = new FoodItemCollectie(itemDoa,itemValidator);




    @Test
    void itShouldFoodItemAanmaken() {
        Mockito.when(itemValidator.valideerWaardes(anyString(), anyDouble(),  anyDouble(),  anyDouble(), anyDouble())).thenReturn(true);
        ItemDTO result = itemCollectie.foodItemAanmaken("test", 100, 10, 20 ,30);
        //Then
        assertTrue(result instanceof FoodItem);

    }


}